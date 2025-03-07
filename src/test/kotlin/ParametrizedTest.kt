import io.github.zerumi.*
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import kotlin.math.PI
import kotlin.test.assertEquals

class ParametrizedTest {
    @ParameterizedTest
    @CsvFileSource(resources = ["/sin.csv"], numLinesToSkip = 1)
    fun `test sin should pass tests from coverage table`(
        input: Double,
        expected: Double
    ) {
        assertEquals(expected, sin(input), 1e-2)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/cos.csv"], numLinesToSkip = 1)
    fun `test cos integration should pass tests from coverage table`(
        input: Double,
        sinValue: Double,
        expected: Double,
    ) {
        mockkStatic(::sin)
        every { sin(PI / 2 - input) } returns sinValue

        assertEquals(expected, cos(input), 1e-4)

        verify { sin(PI / 2 - input) }
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/tan.csv"], numLinesToSkip = 1)
    fun `test tan integration should pass tests from coverage table`(
        input: Double,
        sinValue: Double,
        cosValue: Double,
        expected: Double,
    ) {
        mockkStatic(::sin)
        mockkStatic(::cos)
        every { sin(input) } returns sinValue
        every { cos(input) } returns cosValue

        assertEquals(expected, tan(input), 1e-4)

        verify { cos(input) }
        verify { sin(input) }
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/cot.csv"], numLinesToSkip = 1)
    fun `test cot integration should pass tests from coverage table`(
        input: Double,
        tanValue: Double,
        expected: Double,
    ) {
        mockkStatic(::tan)
        every { tan(input) } returns tanValue

        assertEquals(expected, cot(input), 1e-4)

        verify { tan(input) }
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/sec.csv"], numLinesToSkip = 1)
    fun `test sec integration should pass tests from coverage table`(
        input: Double,
        cosValue: Double,
        expected: Double,
    ) {
        mockkStatic(::cos)
        every { cos(input) } returns cosValue

        assertEquals(expected, sec(input), 1e-4)

        verify { cos(input) }
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/csc.csv"], numLinesToSkip = 1)
    fun `test csc integration should pass tests from coverage table`(
        input: Double,
        sinValue: Double,
        expected: Double,
    ) {
        mockkStatic(::sin)
        every { sin(input) } returns sinValue

        assertEquals(expected, csc(input), 1e-4)

        verify { sin(input) }
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/ln.csv"], numLinesToSkip = 1)
    fun `test ln should pass tests from coverage table`(
        input: Double,
        expected: Double,
    ) {
        assertEquals(expected, ln(input), 1e-1)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/log_3.csv"], numLinesToSkip = 1)
    fun `test log_3 integration should pass tests from coverage table`(
        input: Double,
        lnValue: Double,
        expected: Double,
    ) {
        mockkStatic(::ln)
        every { ln(3.0) } returns 1.098612289
        every { ln(input) } returns lnValue

        assertEquals(expected, log_3(input), 1e-4)

        verify { ln(3.0) }
        verify { ln(input) }
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/log_5.csv"], numLinesToSkip = 1)
    fun `test log_5 integration should pass tests from coverage table`(
        input: Double,
        lnValue: Double,
        expected: Double,
    ) {
        mockkStatic(::ln)
        every { ln(5.0) } returns 1.609437912
        every { ln(input) } returns lnValue

        assertEquals(expected, log_5(input), 1e-4)

        verify { ln(5.0) }
        verify { ln(input) }
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/log_10.csv"], numLinesToSkip = 1)
    fun `test log_10 integration should pass tests from coverage table`(
        input: Double,
        lnValue: Double,
        expected: Double,
    ) {
        mockkStatic(::ln)
        every { ln(10.0) } returns 2.302585092
        every { ln(input) } returns lnValue

        assertEquals(expected, log_10(input), 1e-4)

        verify { ln(10.0) }
        verify { ln(input) }
    }
}