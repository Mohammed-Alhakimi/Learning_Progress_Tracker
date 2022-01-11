class CalculatorTest {
    void testAddition() {
        Calculator calc = new Calculator();
        int result = calc.add(4,6);
        Assertions.assertEquals(10, result);
    }
}