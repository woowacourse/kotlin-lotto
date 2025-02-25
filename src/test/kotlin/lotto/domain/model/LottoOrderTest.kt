package lotto.domain.model

import lotto.domain.value.LottoCount
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoOrderTest {
    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 5])
    fun `수동 로또 수는 전체 로또 수보다 작거나 같다`(count: Int) {
        assertDoesNotThrow {
            val totalLottoCount = LottoCount(5)
            val manualLottoCount = LottoCount(count)
            LottoOrder(totalLottoCount, manualLottoCount)
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [6, 7, 8, 9, 10])
    fun `수동 로또 수가 전체 로또 수보다 크면 예외가 발생한다`(count: Int) {
        assertThrows<IllegalArgumentException> {
            val totalLottoCount = LottoCount(5)
            val manualLottoCount = LottoCount(count)
            LottoOrder(totalLottoCount, manualLottoCount)
        }
    }

    @Test
    fun `총 로또 수가 10이고 수동 로또 수가 4이면 자동 로또 수는 6이다`() {
        // given
        val totalLottoCount = LottoCount(10)
        val manualLottoCount = LottoCount(4)
        val lottoOrder = LottoOrder(totalLottoCount, manualLottoCount)

        // when
        val automaticLottoCount = lottoOrder.getAutomaticLottoCount()

        // then
        assertThat(automaticLottoCount.count).isEqualTo(6)
    }
}
