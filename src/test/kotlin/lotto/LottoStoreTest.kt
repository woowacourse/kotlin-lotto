package lotto

import lotto.model.LottoStore
import lotto.model.generator.ManualLottoGenerator
import lotto.model.generator.RandomLottoGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoStoreTest {
    @Test
    fun `자동 로또를 5개 발행한다`() {
        val lottoStore = LottoStore()
        val purchaseCount = 5
        assertThat(lottoStore.getTickets(purchaseCount, RandomLottoGenerator()).size).isEqualTo(5)
    }

    @Test
    fun `수동 로또를 2개 발행한다`() {
        val lottoStore = LottoStore()
        val manualCount = 2
        val manualLottoGenerator = ManualLottoGenerator()

        manualLottoGenerator.add(listOf(1, 2, 3, 4, 5, 6))
        manualLottoGenerator.add(listOf(3, 4, 5, 6, 7, 8))

        assertThat(lottoStore.getTickets(manualCount, manualLottoGenerator).size).isEqualTo(2)
    }

    @Test
    fun `입력 받은 수동 로또의 개수가 6개가 아니면 에러가 발생한다`() {
        val manualLottoGenerator = ManualLottoGenerator()

        assertThrows<IllegalArgumentException> {
            manualLottoGenerator.add(listOf(1, 2, 3, 4, 5, 6, 7))
        }
    }

    @Test
    fun `입력 받은 수동 로또는 중복되지 않아야 한다`() {
        val manualLottoGenerator = ManualLottoGenerator()

        assertThrows<IllegalArgumentException> {
            manualLottoGenerator.add(listOf(1, 2, 3, 4, 5, 5))
        }
    }

    @Test
    fun `입력 받은 수동 로또는 1부터 45 범위 내에 있어야 한다`() {
        val manualLottoGenerator = ManualLottoGenerator()

        assertThrows<IllegalArgumentException> {
            manualLottoGenerator.add(listOf(1, 2, 3, 4, 5, 46))
        }
    }
}
