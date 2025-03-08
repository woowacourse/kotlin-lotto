package lotto.model

import io.kotest.assertions.Expected
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoStoreCashierTest {
    @Test
    fun `구입하는 돈이 1000원 보다 작으면 에러를 반환한다`() {
        assertThrows<IllegalArgumentException> {
            LottoStoreCashier(900)
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["1000, 1"])
    fun `돈을 주면 구입가능한 수량을 반환한다`(
        money: Int,
        expected: Int,
    ) {
        val actual = LottoStoreCashier(money).calculatePossibleToBuyLottoTicketCount()

        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @CsvSource(value = ["1000, 1, 0", "1500, 1, 500"])
    fun `돈을 주면 고객이 구입을 원하는 수량외 잔액을 계산해 반환한다`(
        money: Int,
        count: Int,
        expected: Int,
    ) {
        val actual = LottoStoreCashier(money).calculateChange(count)

        Assertions.assertThat(actual).isEqualTo(expected)
    }
}