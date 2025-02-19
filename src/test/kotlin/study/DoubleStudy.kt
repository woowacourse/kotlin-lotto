package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.floor

class DoubleStudy {
    @Test
    fun `floor를 통한 double 버림 테스트`() {
        val double: Double = 1.2345678
        assertThat { floor(double * 10) / 10 == 1.2 }
        assertThat { floor(double * 100) / 100 == 1.23 }
        assertThat { floor(double * 1000) / 1000 == 1.234 }
    }
}
