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

    @Test
    fun `스트링에서 정수 배열로 형변환이 가능한지 검증하고 된다면 정수 배열 반환`() {
        val result = Validator.validateConvertToIntList("1,2,3", ",")
        assertThat(result).containsExactly(1, 2, 3)
    }

    @Test
    fun `스트링에서 정수 배열로 형변환이 안된다면 null 반환`() {
        val result = Validator.validateConvertToIntList("1,a,3", ",")
        assertThat(result).isEqualTo(null)
    }

    @Test
    fun `받은 숫자가 PurchaseLottoMoney로 변환이 되는지 검증하고 된다면 반환`() {
        assertThat(1000).isEqualTo(Validator.validateMakePurchaseLottoMoney(1000)?.money)
        assertThat(2500).isEqualTo(Validator.validateMakePurchaseLottoMoney(2500)?.money)
        assertThat(3000).isEqualTo(Validator.validateMakePurchaseLottoMoney(3000)?.money)
    }

    @Test
    fun `받은 숫자가 PurchaseLottoMoney로 변환이 안된다면 null반환`() {
        assertThat(Validator.validateMakePurchaseLottoMoney(500)?.money).isEqualTo(null)
        assertThat(Validator.validateMakePurchaseLottoMoney(999)?.money).isEqualTo(null)
        assertThat(Validator.validateMakePurchaseLottoMoney(0)?.money).isEqualTo(null)
        assertThat(Validator.validateMakePurchaseLottoMoney(-1)?.money).isEqualTo(null)
    }
}
