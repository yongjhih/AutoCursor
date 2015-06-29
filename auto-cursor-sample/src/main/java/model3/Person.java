package model3;

import auto.cursor.AutoCursor;
import android.os.Parcelable;
import java.util.List;
import java.util.Map;
import model1.HeightBucket;
import model2.Address;

@AutoCursor
public abstract class Person implements Parcelable {
  public abstract String name();
  public abstract long id();

  @AutoCursor.Builder
  public abstract static class Builder {
      public abstract Builder name(String s);
      public abstract Builder id(long n);
      public abstract Person build();
  }

  public static Builder builder() {
      return new AutoCursor_Person.Builder();
  }

  public abstract Builder toBuilder();

}
