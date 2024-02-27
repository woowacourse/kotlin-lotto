package lotto.model

import lotto.exception.Exceptions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PriceTest {
    @ParameterizedTest
    @CsvSource("1000", "2000", "6000", "8000")
    fun `구입할 수 있는 로또 개수보다 수동으로 구매할 로또 개수가 크면 ManualPurchaseCountTooLargeException 예외처리가 발생한다`(strMoney: String) {
        val lottoMachine = Price().plusPrice(strMoney)

        assertThrows<Exceptions.ManualPurchaseCountTooLargeException> {
            lottoMachine.getRandomLottoCount(10)
        }
    }

    @ParameterizedTest
    @CsvSource("0", "999")
    fun `구입 금액은 자연수이면서 1000 이상이다`(strMoney: String) {
        assertThrows<Exceptions.InvalidPurchaseAmountException> {
            Price().plusPrice(strMoney)
        }
    }

    @ParameterizedTest
    @CsvSource("asdf", "한글")
    fun `구입 금액은 자연수여야 합니다`(strMoney: String) {
        assertThrows<Exceptions.PurchaseAmountNotNaturalNumberException> {
            Price().plusPrice(strMoney)
        }
    }

    @ParameterizedTest
    @CsvSource("1000", "1100", "1500", "1999")
    fun `1000으로 1장의 티켓을 받는다`(strMoney: String) {
        val price = Price().plusPrice(strMoney)
        assertThat(price.getNumberOfLottoTickets()).isEqualTo(1)
    }
}
