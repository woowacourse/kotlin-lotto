package lotto.model

import lotto.exception.Exceptions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class KioskTest {
    @ParameterizedTest
    @CsvSource("1000", "2000", "6000", "8000")
    fun `구입할 수 있는 로또 개수보다 수동으로 구매할 로또 개수가 크면 ManualPurchaseCountTooLargeException 예외처리가 발생한다`(strMoney: String) {
        val kiosk = Kiosk()
        kiosk.addDeposit(Money.from(strMoney))
        assertThrows<Exceptions.ManualPurchaseCountTooLargeException> {
            kiosk.getRandomLottoCount(10)
        }
    }

    @ParameterizedTest
    @CsvSource("0", "999")
    fun `구입 금액은 자연수이면서 1000 이상이다`(strMoney: String) {
        val kiosk = Kiosk()
        assertThrows<Exceptions.InvalidPurchaseAmountException> {
            kiosk.addDeposit(Money.from(strMoney))
        }
    }

    @ParameterizedTest
    @CsvSource("1000", "1100", "1500", "1999")
    fun `1000으로 1장의 티켓을 받는다`(strMoney: String) {
        val kiosk = Kiosk()
        kiosk.addDeposit(Money.from(strMoney))
        assertThat(kiosk.getNumberOfLottoTickets()).isEqualTo(1)
    }

    @Test
    fun `현재 가지고 있는 예치금에서 로또를 사면 예치금에서 로또를 산 비용이 빠진다`() {
        val kiosk = Kiosk()
        kiosk.addDeposit(Money(19999))
        kiosk.useDepositForLottoTickets()
        assertThat(kiosk.deposit.money.value).isEqualTo(1)
    }
}
