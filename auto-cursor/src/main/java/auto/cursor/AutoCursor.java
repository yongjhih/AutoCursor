/*
 * Copyright (C) 2012 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package auto.cursor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies that <a href="https://github.com/frankiesardo/auto.cursor">AutoCursor</a> should
 * generate an implementation class for the annotated abstract class, implementing the standard
 * {@link Object} methods like {@link Object#equals equals} to have conventional value semantics. A
 * simple example: <pre>
 *
 *   &#64;AutoCursor
 *   abstract class Person implements Parcelable {
 *     static Person create(String name, int id) {
 *       return new AutoCursor_Person(name, id);
 *     }
 *
 *     abstract String name();
 *     abstract int id();
 *   }</pre>
 *
 * @see <a href="https://github.com/frankiesardo/auto.cursor">AutoCursor User's Guide</a>
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface AutoCursor {

  /**
   * Specifies that AutoCursor should generate an implementation of the annotated class or interface,
   * to serve as a <i>builder</i> for the value-type class it is nested within. As a simple example,
   * here is an alternative way to write the {@code Person} class mentioned in the {@link AutoCursor}
   * example: <pre>
   *
   *   &#64;AutoCursor
   *   abstract class Person {
   *     static Builder builder() {
   *       return new AutoCursor_Person.Builder();
   *     }
   *
   *     abstract String name();
   *     abstract int id();
   *
   *     &#64;AutoCursor.Builder
   *     interface Builder {
   *       Builder name(String x);
   *       Builder id(int x);
   *       Person build();
   *     }
   *   }</pre>
   *
   * <p><b>This API is provisional and subject to change.</b></p>
   *
   */
  @Retention(RetentionPolicy.SOURCE)
  @Target(ElementType.TYPE)
  public @interface Builder {
  }

  /**
   * Specifies that the annotated method is a validation method. The method should be a non-private
   * no-argument method in an AutoCursor class. It will be called by the {@code build()} method of
   * the {@link Builder @AutoCursor.Builder} implementation, immediately after constructing the new
   * object. It can throw an exception if the new object fails validation checks.
   */
  @Retention(RetentionPolicy.SOURCE)
  @Target(ElementType.METHOD)
  public @interface Validate {
  }

  @Retention(RetentionPolicy.SOURCE)
  @Target(ElementType.METHOD)
  public @interface Column { // for de/serialization
    /**
     * The name(s) of this field in JSON. Use an array if this could be represented by multiple names.
     * Note that using this field will override the enclosing JsonObject's fieldNamingPolicy.
     */
    String[] name() default {};

    /** The TypeConverter that will be used to parse/serialize this variable. */
    Class typeConverter() default void.class;
  }
}
