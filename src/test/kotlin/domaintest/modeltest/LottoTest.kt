package domaintest.modeltest

import domain.lottogenerator.ManualLottoGenerator
import domain.lottogenerator.WinningLottoGenerator
import domain.model.lotto.LottoNumber
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
    fun `특정 로또가 다른 로또와 같은 숫자의 개수를 구하기`() {
        val lotto = manualLottoGenerator.generate(listOf(1, 2, 3, 4, 5, 6))
        val otherLotto = manualLottoGenerator.generate(listOf(1, 2, 3, 4, 5, 6))

        assertThat(lotto.getMatchCount(otherLotto)).isEqualTo(6)
    }

    @Test
    fun `특정 로또안에 다른 로또 숫자가 포함되어 있는 경우를 확인`() {
        val lotto = manualLottoGenerator.generate(listOf(1, 2, 3, 4, 5, 6))
        val lottoNumber = LottoNumber.from(6)

        assertThat(lotto.contains(lottoNumber)).isTrue
    }

    @Test
    fun `특정 로또안에 다른 로또 숫자가 포함되어 있지 않는 경우를 확인`() {
        val lotto = manualLottoGenerator.generate(listOf(1, 2, 3, 4, 5, 6))
        val lottoNumber = LottoNumber.from(7)

        assertThat(lotto.contains(lottoNumber)).isFalse
    }
}
