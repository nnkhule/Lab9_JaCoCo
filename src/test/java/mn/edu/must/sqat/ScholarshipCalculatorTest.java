package mn.edu.must.sqat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ScholarshipCalculatorTest {
    private final ScholarshipCalculator calculator = new ScholarshipCalculator();

    @Test
    void letterGradeA() {
        assertEquals("A", calculator.letterGrade(95));
    }

    @Test
    void gpaAverage() {
        assertEquals(3.5, calculator.gpa(new double[]{95, 85}), 0.001);
    }

    @Test
    void scholarshipFull() {
        assertEquals(100, calculator.scholarship(3.9, 16, false));
    }

    @Test
    void scholarshipPartial() {
        assertEquals(70, calculator.scholarship(3.5, 12, false));
    }

    @Test
    void scholarshipViolation() {
        assertEquals(0, calculator.scholarship(3.9, 16, true));
    }

    @Test
    void letterGradeBoundaryScores() {
        assertEquals("F", calculator.letterGrade(0));
        assertEquals("A", calculator.letterGrade(100));
        assertEquals("B", calculator.letterGrade(85));
        assertEquals("C", calculator.letterGrade(75));
        assertEquals("D", calculator.letterGrade(65));
    }

    @Test
    void letterGradeSpecificationBoundaries() {
        assertEquals("D", calculator.letterGrade(60));
        assertEquals("F", calculator.letterGrade(59.99));
        assertEquals("D", calculator.letterGrade(69.99));
        assertEquals("F", calculator.letterGrade(0));
        assertEquals("A", calculator.letterGrade(100));
        assertEquals("B", calculator.letterGrade(89.99));
        assertEquals("A", calculator.letterGrade(90));
    }

    @Test
    void letterGradeBelowZeroThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(-1));
    }

    @Test
    void letterGradeAboveHundredThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(101));
    }

    @Test
    void gpaNullScoresThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(null));
    }

    @Test
    void gpaEmptyScoresThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(new double[]{}));
    }

    @Test
    void gpaSingleScoreLoopBoundary() {
        assertEquals(4.0, calculator.gpa(new double[]{95}), 0.001);
    }

    @Test
    void gpaMultipleScoresLoopBoundary() {
        assertEquals(2.5, calculator.gpa(new double[]{95, 85, 75, 65}), 0.001);
    }

    @Test
    void gpaFGradeUsesDefaultSwitchArm() {
        assertEquals(0.0, calculator.gpa(new double[]{50}), 0.001);
    }

    @Test
    void gpaCAndDGradesUseSwitchArms() {
        assertEquals(1.5, calculator.gpa(new double[]{75, 65}), 0.001);
    }

    @Test
    void scholarshipGpaBelowZeroThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(-0.1, 0, false));
    }

    @Test
    void scholarshipGpaAboveFourThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(4.1, 0, false));
    }

    @Test
    void scholarshipNegativeCreditsThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(0, -1, false));
    }

    @Test
    void scholarshipGpaQualifiesButCreditsBelowFifteen() {
        assertEquals(70, calculator.scholarship(3.9, 14, false));
    }

    @Test
    void scholarshipGpaBelowThirtyFourGetsFifty() {
        assertEquals(50, calculator.scholarship(3.2, 16, false));
    }

    @Test
    void scholarshipGpaQualifiesButCreditsBelowTwelve() {
        assertEquals(50, calculator.scholarship(3.5, 11, false));
    }

    @Test
    void scholarshipGpaBelowThreeGetsZero() {
        assertEquals(0, calculator.scholarship(2.9, 16, false));
    }
}
