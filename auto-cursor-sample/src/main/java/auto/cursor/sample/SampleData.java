package auto.cursor.sample;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import model1.HeightBucket;
import model2.Address;
import model3.Person;

public interface SampleData {

  static final Person ALICE = Person.builder().name("Alice").id(1L).build();

  static final Person BOB = Person.builder().name("Bob").id(2L).build();
}
