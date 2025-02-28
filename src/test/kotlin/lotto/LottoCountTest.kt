package lotto

import lotto.model.LottoCount
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoCountTest {
    @Test
    fun `수동 로또 개수가 총 로또 개수를 초과할 경우 예외를 발생한다`() {
        val totalLottoCount = LottoCount(10)
        val manualLottoCount = LottoCount(11)

        assertThrows<IllegalArgumentException> { totalLottoCount.subtract(manualLottoCount) }
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
