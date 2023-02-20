package domaintest.modeltest

import domain.lottogenerator.ManualLottoGenerator
import domain.lottogenerator.WinningLottoGenerator
import domain.model.LottoResult
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class LottoTest {

    private lateinit var manualLottoGenerator: ManualLottoGenerator
    private lateinit var winningLottoGenerator: WinningLottoGenerator

    @BeforeEach
    fun setUp() {
        manualLottoGenerator = ManualLottoGenerator()
        winningLottoGenerator = WinningLottoGenerator()
    }

    @Test
    fun `로또의 번호가 6개인 경우`() {
        assertDoesNotThrow {
            manualLottoGenerator.generate(listOf(1, 2, 3, 4, 5, 6))
        }
    }

    @Test
    fun `로또의 번호가 6개가 아닌 경우 예외가 발생`() {
        assertThrows<IllegalArgumentException> {
            manualLottoGenerator.generate(listOf(1, 2, 3, 4, 5, 6, 7))
        }
    }

    @Test
    fun `로또의 번호가 6개가 일치하는 경우 (1등)`() {
        val catchLotto = manualLottoGenerator.generate(listOf(1, 2, 3, 4, 5, 6))

        val actual = catchLotto.getLottoResult(
            winningLottoGenerator.generateWinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
        )

        assertThat(actual).isEqualTo(LottoResult.FIRST)
    }

    @Test
    fun `로또의 번호가 5개가 일치하고 보너스 번호가 일치하는 경우 (2등)`() {
        val lotto = manualLottoGenerator.generate(listOf(1, 2, 3, 4, 5, 6))

        val actual = lotto.getLottoResult(
            winningLottoGenerator.generateWinningLotto(listOf(1, 2, 3, 4, 5, 8), 6)
        )

        assertThat(actual).isEqualTo(LottoResult.SECOND)
    }

    @Test
    fun `로또의 번호가 5개가 일치하고 보너스 번호가 일치하지 않는 경우 (3등)`() {
        val lotto = manualLottoGenerator.generate(listOf(1, 2, 3, 4, 5, 6))

        val actual = lotto.getLottoResult(
            winningLottoGenerator.generateWinningLotto(listOf(1, 2, 3, 4, 5, 8), 7)
        )

        assertThat(actual).isEqualTo(LottoResult.THIRD)
    }

    @Test
    fun `로또의 번호가 4개가 일치하는 경우 (4등)`() {
        val lotto = manualLottoGenerator.generate(listOf(1, 2, 3, 4, 5, 6))

        val actual = lotto.getLottoResult(
            winningLottoGenerator.generateWinningLotto(listOf(1, 2, 3, 4, 41, 42), 43)
        )

        assertThat(actual).isEqualTo(LottoResult.FORTH)
    }

    @Test
    fun `로또의 번호가 3개가 일치하는 경우 (5등)`() {
        val lotto = manualLottoGenerator.generate(listOf(1, 2, 3, 4, 5, 6))

        val actual = lotto.getLottoResult(
            winningLottoGenerator.generateWinningLotto(listOf(1, 2, 3, 35, 41, 42), 43)
        )

        assertThat(actual).isEqualTo(LottoResult.FIFTH)
    }
}
