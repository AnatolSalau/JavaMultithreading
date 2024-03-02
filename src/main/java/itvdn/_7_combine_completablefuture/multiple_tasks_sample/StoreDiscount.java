package itvdn._7_combine_completablefuture.multiple_tasks_sample;

import lombok.Data;
import lombok.Getter;

import java.util.Objects;

public class StoreDiscount {
      private final Integer numberOfStore;
      private final Integer sizeOfDiscount;

      public StoreDiscount(Integer numberOfStore, Integer sizeOfDiscount) {
            this.numberOfStore = numberOfStore;
            this.sizeOfDiscount = sizeOfDiscount;
      }

      @Override
      public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StoreDiscount that = (StoreDiscount) o;
            return Objects.equals(numberOfStore, that.numberOfStore) && Objects.equals(sizeOfDiscount, that.sizeOfDiscount);
      }

      @Override
      public int hashCode() {
            return Objects.hash(numberOfStore, sizeOfDiscount);
      }

      public Integer getNumberOfStore() {
            return numberOfStore;
      }

      public Integer getSizeOfDiscount() {
            return sizeOfDiscount;
      }

      @Override
      public String toString() {
            return "StoreDiscount{" +
                  "numberOfStore=" + numberOfStore +
                  ", sizeOfDiscount=" + sizeOfDiscount +
                  '}';
      }
}
