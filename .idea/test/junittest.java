import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JunitTest {

    @Test
    public void testGroupAssertions() {
        int[] numbers = {0, 1, 2, 3, 4};
        Assertions.assertAll("numbers",
                () -> Assertions.assertEquals(numbers[1], 1),
                () -> Assertions.assertEquals(numbers[3], 3),
                () -> Assertions.assertEquals(numbers[4], 4)
        );
    }
    @Test
    @DisplayName("重复测试")
    @RepeatedTest(value = 3)
    public void i_am_a_repeated_test() {
        System.out.println("执行测试");
    }
    @DisplayName("自定义名称重复测试")
    @RepeatedTest(value = 3, name = "{displayName} 第 {currentRepetition} 次")
    public void i_am_a_repeated_test_2() {
        System.out.println("执行测试");
    }

    @Test
    @DisplayName("超时方法测试")
    public void test_should_complete_in_one_second() {
        Assertions.assertTimeoutPreemptively(Duration.of(3, ChronoUnit.SECONDS), () -> Thread.sleep(2000));
    }


    @ParameterizedTest
    @ValueSource(ints = {2, 5, 8})
    public void testNumberShouldBeEven(int  num) {
        Assertions.assertEquals(0, num % 2);
    }



    @Order(3)
    @ParameterizedTest
    @ValueSource(strings = {"Effective Java", "Code Complete", "Clean Code"})
    public void testPrintTitle(String titles) {
        System.out.println(titles);
    }

    @DisplayName("读取csvsource")
    @Order(2)
    @ParameterizedTest
    @CsvSource({"1,One", "2,Two", "3,Three"})
    public void testDataFromCsv(int id, String name) {
        System.out.printf("id: %d, name: %s ", id, name);
    }


    @DisplayName("读取csv 文件")
    @Order(1)
    @ParameterizedTest
    @CsvFileSource(resources = "/resources/cangkudetail1.csv",numLinesToSkip =1)
    public void testDataFromCsvfile(long id, int kucun) {
        System.out.printf("id: %d, kucun: %d \n", id, kucun);
    }



}