package lotto

import lotto.model.LottoCount
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoCountTest {
    @ParameterizedTest
    @CsvSource(
        "10, 11, false",
        "11, 11, true",
    )
    fun `로또 개수 구매 가능 여부 테스트`(
        totalLottoCountValue: Int,
        manualLottoCountValue: Int,
        expectedResult: Boolean,
    ) {
        val totalLottoCount = LottoCount(totalLottoCountValue)
        val manualLottoCount = LottoCount(manualLottoCountValue)

        val result = totalLottoCount.isAvailablePurchase(manualLottoCount)

        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    fun `총 로또 개수에서 자동 로또 개수를 뺀 값을 반환한다`() {
        val totalLottoCount = LottoCount(15)
        val manualLottoCount = LottoCount(11)

        val actual: Int = totalLottoCount.subtract(manualLottoCount).count

        assertThat(actual).isEqualTo(4)
    }

    @Test
    fun `로또 개수가 0개 미만일 경우 예외를 발생한다`() {
        assertThrows<IllegalArgumentException> { LottoCount(-1) }
    }
}
