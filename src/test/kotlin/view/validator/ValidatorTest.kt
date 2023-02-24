package view.validator

import common.convertToLotto
import common.convertToLottoNumberSet
import domain.LottoNumber
import domain.PurchaseLottoMoney
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

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
        assertThat(result).isNull()
    }

    @Test
    fun `스트링에서 정수 배열로 형변환이 가능한지 검증하고 된다면 정수 배열 반환`() {
        val result = Validator.validateConvertToIntList("1,2,3", ",")
        assertThat(result).containsExactly(1, 2, 3)
    }

    @Test
    fun `스트링에서 정수 배열로 형변환이 안된다면 null 반환`() {
        val result = Validator.validateConvertToIntList("1,a,3", ",")
        assertThat(result).isNull()
    }

    @Test
    fun `받은 숫자가 PurchaseLottoMoney로 변환이 되는지 검증하고 된다면 반환`() {
        assertThat(1000).isEqualTo(Validator.validateMakePurchaseLottoMoney(1000)?.money)
        assertThat(2500).isEqualTo(Validator.validateMakePurchaseLottoMoney(2500)?.money)
        assertThat(3000).isEqualTo(Validator.validateMakePurchaseLottoMoney(3000)?.money)
    }

    @Test
    fun `받은 숫자가 PurchaseLottoMoney로 변환이 안된다면 null반환`() {
        assertThat(Validator.validateMakePurchaseLottoMoney(500)?.money).isNull()
        assertThat(Validator.validateMakePurchaseLottoMoney(999)?.money).isNull()
        assertThat(Validator.validateMakePurchaseLottoMoney(0)?.money).isNull()
        assertThat(Validator.validateMakePurchaseLottoMoney(-1)?.money).isNull()
    }

    @Test
    fun `주어진 돈으로 그만큼의 수동 구매 개수를 구매가능한지 검증하고 된다면 null이 아닌 값을 반환`() {
        val money = PurchaseLottoMoney(3500)
        val manualCount = 3
        val result = Validator.validateMakeLottoPurchaseInfo(money, manualCount)
        assertThat(result).isNotNull
    }

    @Test
    fun `주어진 돈으로 그만큼의 수동 구매 개수를 구매가능한지 검증하고 실패한다면 null을 반환`() {
        val money = PurchaseLottoMoney(3500)
        val manualCount = 4
        val result = Validator.validateMakeLottoPurchaseInfo(money, manualCount)
        assertThat(result).isNull()
    }

    @Test
    fun `주어진 배열이 Lotto로 변환에 성공해서 반환`() {
        val numbers = listOf(1, 2, 3, 4, 5, 6)
        val result = Validator.validateMakeLotto(numbers)
        assertThat(result?.numbers).containsAll(setOf(1, 2, 3, 4, 5, 6).convertToLottoNumberSet())
    }

    @Test
    fun `주어진 배열이 Lotto로 변환에 실패해서 null반환`() {
        val numbers = listOf(1, 2, 3, 4, 5, 5)
        val result = Validator.validateMakeLotto(numbers)
        assertThat(result?.numbers).isNull()
    }

    @ValueSource(ints = [1, 2, 3, 45])
    @ParameterizedTest
    fun `주어진 숫자가 LottoNumber로 변환에 성공해서 반환`(n: Int) {
        val number = Validator.validateMakeLottoNumber(n)
        val expected = LottoNumber.from(n)
        assertThat(number).isEqualTo(expected)
    }

    @ValueSource(ints = [-1, 0, 46])
    @ParameterizedTest
    fun `주어진 숫자가 LottoNumber로 변환에 실패해서 null반환`(n: Int) {
        val number = Validator.validateMakeLottoNumber(n)
        assertThat(number).isNull()
    }

    @Test
    fun `주어진 Lotto와 LottoNumber로 WinningLotto가 성공적으로 만들어짐`() {
        val lotto = setOf(1, 2, 3, 4, 5, 6).convertToLotto()
        val bonusNumber = LottoNumber.from(7)
        val winningLotto = Validator.validateMakeWinningLotto(lotto, bonusNumber)
        assertThat(winningLotto).isNotNull
    }

    @Test
    fun `주어진 Lotto와 LottoNumber로 WinningLotto를 만드는데 실패`() {
        val lotto = setOf(1, 2, 3, 4, 5, 6).convertToLotto()
        val bonusNumber = LottoNumber.from(6)
        val winningLotto = Validator.validateMakeWinningLotto(lotto, bonusNumber)
        assertThat(winningLotto).isNull()
    }
}
