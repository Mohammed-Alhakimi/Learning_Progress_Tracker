package tracker;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class VerifyTest {

    Verify Verify;

    @BeforeEach
    void setUpVerify(){
        Verify= new Verify();
    }

    @Test
    @DisplayName("Valid first name tester")
    void validFirstNameTest() {
        String validName1 = "J-Clause";
        String validName2 = "O'Neil";
        String validName3 = "Ad'na-n";

        assertAll(
                ()->  assertTrue(Verify.firstName(validName1), validName1),
                ()->  assertTrue(Verify.firstName(validName2), validName2),
                ()->  assertTrue(Verify.firstName(validName3), validName3)
        );
    }
    @ParameterizedTest
    @MethodSource("entriesForPointsValid")
    void testPointsValidity(String input){
        boolean valid = Verify.points(input);
        assertTrue(valid);
    }

    @ParameterizedTest
    @MethodSource("entriesForPointsInvalid")
    void testPointsInvalidity(String input){
        boolean valid = Verify.points(input);
        assertFalse(valid);
    }

    @Test
    @DisplayName("Invalid first name tester")
    void invalidFirstNameTest() {
        String invalidName1 = "-Jj";
        String invalidName2 = "j.";
        String invalidName3 = "jkij-jinhmk-'hk";

        assertAll(
                ()->assertFalse(Verify.firstName(invalidName1), invalidName1),
                ()->assertFalse(Verify.firstName(invalidName2), invalidName2),
                ()->assertFalse(Verify.firstName(invalidName3), invalidName3)
        );
    }

    @ParameterizedTest(name = "Last Name Test {index} => is ({0}) valid ??")
    @CsvSource({"roba jabour","ali alhibsi","Doe doe doe",})
    void validLastNameTest(String name) {
        boolean expected = Verify.lastName(name);
        assertTrue(expected);
    }

    @ParameterizedTest(name = "Last Name Test {index} => is({0}) invalid ??")
    @CsvSource({"D" ,".D","goe yo'' sup'l",})
    void invalidLastNameTest(String name) {
        boolean expected = Verify.lastName(name);
        assertFalse(expected);
    }

    @ParameterizedTest(name = "Email Test {index} => is({0}) valid ??")
    @MethodSource("makeValidEmails")
    void validEmailTest(String email) {
        boolean valid =Verify.email(email);
        assertTrue(valid);
    }

    @ParameterizedTest(name = "Email Test {index} => is({0}) valid ??")
    @MethodSource("makeNonValidEmails")
    void invalidEmailTest(String email) {
        boolean isValid = Verify.email(email);
        assertFalse(isValid);
    }

    private static List<String> makeValidEmails(){
        return List.of(
                "jdoe@hotmail.net"
                ,"jane.doe@yahoo.com"
                ,"jc@live.co"
                ,"robajabour2@gmail.com");
    }
    private static List<String> makeNonValidEmails(){
        return List.of(
                "email"
                ,"@email"
                ,"email@"
                ,"robajabour2@@gmail.com");
    }
    private static List<String> entriesForPointsValid(){
        return List.of("1000 1 1 1 1",
                "2312 2 2 0 0",
                "-123 2 0 1 0"
        );
    }
    private static List<String> entriesForPointsInvalid() {
        return List.of("1000 1 1 -1 1",
                "2312 2 2 0 ?",
                "-123 2 0 1 "
        );
    }

}