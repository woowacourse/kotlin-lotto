package view.validator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ValidatorTest {
    @Test
    fun `스트링에서 정수로 형변환 가능한지 검증하고 된다면 정수 반환`() {
        val result = Validator.validateConvertInt("3")
        val expected = 3
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `스트링에서 정수로 형변환이 안된다면 null반환`() {
        val result = Validator.validateConvertInt("3,")
        val expected = null
        assertThat(result).isEqualTo(expected)
    }
}
