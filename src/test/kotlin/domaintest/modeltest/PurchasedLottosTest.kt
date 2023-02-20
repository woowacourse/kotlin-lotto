package domaintest.modeltest

import domain.lottogenerator.ManualLottoGenerator
import domain.lottogenerator.WinningLottoGenerator
import domain.model.LottoResult
import domain.model.lotto.PurchasedLottos
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PurchasedLottosTest {

    private lateinit var manualLottoGenerator: ManualLottoGenerator
    private lateinit var winningLottoGenerator: WinningLottoGenerator

    @BeforeEach
    fun setUp() {
        manualLottoGenerator = ManualLottoGenerator()
        winningLottoGenerator = WinningLottoGenerator()
    }

    @Test
    fun `구입한 로또의 전체 당첨 결과를 확인하기`() {
        val purchasedLottos = PurchasedLottos(
            lottos = listOf(
                manualLottoGenerator.generate(listOf(1, 2, 3, 4, 5, 6)),
                manualLottoGenerator.generate(listOf(1, 2, 3, 4, 5, 7)),
                manualLottoGenerator.generate(listOf(1, 2, 3, 4, 7, 8))
            )
        )

        assertThat(
            purchasedLottos.getTotalLottoResults(
                winningLottoGenerator.generateWinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
            )
        ).isEqualTo(
            listOf(
                LottoResult.FIRST,
                LottoResult.SECOND,
                LottoResult.FORTH
            )
        )
    }
}
