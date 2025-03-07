import io.github.zerumi.*
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import kotlin.math.PI
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ModuleTest {
    @Test
    fun `test sin should return 1,0 on x = pi div 2`() {
        assertEquals(1.0, sin(PI / 2), 1e-3)
    }

    @Test
    fun `test sin should return 0,5 on x = pi div 6`() {
        assertEquals(0.5, sin(PI / 6), 1e-2)
    }

    @Test
    fun `test sin should return 0,0 on x = 0,0`() {
        assertEquals(0.0, sin(0.0), 1e-1)
    }

    @Test
    fun `test cos should pass integration with sin`() {
        mockkStatic(::sin)
        every { sin(0.0) } returns 0.0

        assertEquals(1.0, cos(0.0))

        verify { sin(0.0) }
    }

    @Test
    fun `test tan should pass integration with sin and cos`() {
        mockkStatic(::sin)
        mockkStatic(::cos)
        every { sin(0.0) } returns 0.0
        every { cos(0.0) } returns 1.0

        assertEquals(0.0, tan(0.0))

        verify { cos(0.0) }
        verify { sin(0.0) }
    }

    @Test
    fun `test cot should pass integration with tan`() {
        mockkStatic(::tan)
        every { tan(0.5) } returns 0.5463

        assertEquals(1.8304960644, cot(0.5), 1e-4)

        verify { tan(0.5) }
        verify(exactly = 0) { cos(0.5) }
        verify(exactly = 0) { sin(0.5) }
    }

    @Test
    fun `test sec should pass integration with cos`() {
        mockkStatic(::cos)
        every { cos(0.5) } returns 0.87758

        assertEquals(1.13949, sec(0.5), 1e-4)

        verify { cos(0.5) }
    }

    @Test
    fun `test csc should pass integration with sin`() {
        mockkStatic(::sin)
        every { sin(0.5) } returns 0.47943

        assertEquals(2.0858, csc(0.5), 1e-4)

        verify { sin(0.5) }
    }

    @Test
    fun `test ln should return 0,0 on x = 1,0`() {
        assertEquals(0.0, ln(1.0), 1e-2)
    }

    @Test
    fun `test ln correct return 0,693 on x = 2,0`() {
        assertEquals(0.693, ln(2.0), 1e-2)
    }

    @Test
    fun `test ln should fail on zero value`() {
        assertFailsWith<IllegalArgumentException> { ln(0.0) }
    }

    @Test
    fun `test log_3 should pass integration with ln`() {
        mockkStatic(::ln)
        every { ln(3.0) } returns 1.098612289
        every { ln(10.0) } returns 2.302585092

        assertEquals(2.09590, log_3(10.0), 1e-4)

        verify { ln(3.0) }
        verify { ln(10.0) }
    }

    @Test
    fun `test log_5 should pass integration with ln`() {
        mockkStatic(::ln)
        every { ln(5.0) } returns 1.609437912
        every { ln(10.0) } returns 2.302585092

        assertEquals(1.43067, log_5(10.0), 1e-4)

        verify { ln(5.0) }
        verify { ln(10.0) }
    }

    @Test
    fun `test log_10 should pass integration with ln`() {
        mockkStatic(::ln)
        every { ln(10.0) } returns 2.302585092
        every { ln(15.0) } returns 2.708050201

        assertEquals(1.17609, log_10(15.0), 1e-4)

        verify { ln(10.0) }
        verify { ln(15.0) }
    }
}
