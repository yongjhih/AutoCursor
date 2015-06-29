package model2;

import auto.cursor.AutoCursor;
import android.os.Parcelable;

@AutoCursor
public abstract class Address implements Parcelable {
  public abstract double[] coordinates();
  public abstract String cityName();

  public static Address create(double[] coordinates, String cityName) {
      return builder().coordinates(coordinates).cityName(cityName).build();
  }

  public static Builder builder() {
      return new AutoCursor_Address.Builder();
  }

  @AutoCursor.Builder
  public interface Builder {
      public Builder coordinates(double[] x);
      public Builder cityName(String x);
      public Address build();
  }

  @AutoCursor.Validate
  public void validate() {
      if (cityName().length() < 2) {
          throw new IllegalStateException("Not a city name");
      }
  }
}
