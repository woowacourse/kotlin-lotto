package view.validator

import domain.Lotto
import domain.LottoNumber
import domain.PurchaseLottoMoney
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ValidatorTest {
    @Test
    fun `스트링에서 정수로 형변환 가능한지 검증하고 된다면 true반환`() {
        val result = Validator.validateConvertInt("3")
        assertThat(result).isTrue
    }

    @Test
    fun `스트링에서 정수로 형변환이 안된다면 false반환`() {
        val result = Validator.validateConvertInt("3,")
        assertThat(result).isFalse
    }

    @Test
    fun `스트링에서 정수 배열로 형변환이 가능한지 검증에 성공한다면 true반환`() {
        val result = Validator.validateConvertToIntList("1,2,3", ",")
        assertThat(result).isTrue
    }

    @Test
    fun `스트링에서 정수 배열로 형변환이 가능한지 검증에 실패한다면 false반환`() {
        val result = Validator.validateConvertToIntList("1,a,3", ",")
        assertThat(result).isFalse
    }

    @Test
    fun `받은 숫자가 PurchaseLottoMoney로 변환이 되는지 검증하고 된다면 true반환`() {
        assertThat(Validator.validateMakePurchaseLottoMoney(1000)).isTrue
        assertThat(Validator.validateMakePurchaseLottoMoney(2500)).isTrue
        assertThat(Validator.validateMakePurchaseLottoMoney(3000)).isTrue
    }

    @Test
    fun `받은 숫자가 PurchaseLottoMoney로 변환이 안된다면 false반환`() {
        assertThat(Validator.validateMakePurchaseLottoMoney(500)).isFalse
        assertThat(Validator.validateMakePurchaseLottoMoney(999)).isFalse
        assertThat(Validator.validateMakePurchaseLottoMoney(0)).isFalse
        assertThat(Validator.validateMakePurchaseLottoMoney(-1)).isFalse
    }

    @Test
    fun `주어진 돈으로 그만큼의 수동 구매 개수를 구매가능한지 검증하고 된다면 true를 반환`() {
        val money = PurchaseLottoMoney(3500)
        val manualCount = 3
        val result = Validator.validateMakeLottoPurchaseInfo(money, manualCount)
        assertThat(result).isTrue
    }

    @Test
    fun `주어진 돈으로 그만큼의 수동 구매 개수를 구매가능한지 검증하고 실패한다면 false을 반환`() {
        val money = PurchaseLottoMoney(3500)
        val manualCount = 4
        val result = Validator.validateMakeLottoPurchaseInfo(money, manualCount)
        assertThat(result).isFalse
    }

    @Test
    fun `주어진 배열이 Lotto로 변환에 성공하면 True반환`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val result = Validator.validateMakeLotto(numbers)
        assertThat(result).isTrue
    }

    @Test
    fun `주어진 배열이 Lotto로 변환에 실패해서 False반환`() {
        val numbers = listOf(1, 2, 3, 4, 5, 5)
        val result = Validator.validateMakeLotto(numbers)
        assertThat(result).isFalse
    }

    @ValueSource(ints = [1, 2, 3, 45])
    @ParameterizedTest
    fun `주어진 숫자가 LottoNumber로 변환에 성공하면 True반환`(n: Int) {
        val number = Validator.validateMakeLottoNumber(n)
        assertThat(number).isTrue
    }

    @ValueSource(ints = [-1, 0, 46])
    @ParameterizedTest
    fun `주어진 숫자가 LottoNumber로 변환에 실패해서 False반환`(n: Int) {
        val number = Validator.validateMakeLottoNumber(n)
        assertThat(number).isFalse
    }

    @Test
    fun `주어진 Lotto와 LottoNumber로 WinningLotto가 성공적으로 만들어지면 True반환`() {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.from(7)
        val result = Validator.validateMakeWinningLotto(lotto, bonusNumber)
        assertThat(result).isTrue
    }

    @Test
    fun `주어진 Lotto와 LottoNumber로 WinningLotto를 만드는데 실패하면 False반환`() {
        val lotto = Lotto(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.from(6)
        val result = Validator.validateMakeWinningLotto(lotto, bonusNumber)
        assertThat(result).isFalse
    }
}
