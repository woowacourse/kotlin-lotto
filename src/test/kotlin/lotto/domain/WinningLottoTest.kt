package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.WinningLotto
import lotto.domain.value.LottoNumber
import lotto.enums.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    private lateinit var winningNumbers1to6: Lotto
    private val bonusNumber45: LottoNumber = LottoNumber(45)

    @BeforeEach
    fun setUp() {
        val lottoNumbers = (1..6).map { LottoNumber(it) }.toSet()
        winningNumbers1to6 = Lotto(lottoNumbers)
    }

    @Test
    fun `당첨 번호와 보너스 번호는 중복되지 않는다`() {
        assertDoesNotThrow {
            WinningLotto(winningNumbers1to6, bonusNumber45)
        }
    }

    @Test
    fun `당첨 번호와 보너스 번호가 중복되면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val bonusNumber = LottoNumber(1)
            WinningLotto(winningNumbers1to6, bonusNumber)
        }
    }

    @Test
    fun `일치하는 번호가 5개이고 보너스 번호가 일치하면 등수는 2등이다`() {
        val lottoNumbers = (1..5).map { LottoNumber(it) }.toMutableSet()
        lottoNumbers.add(LottoNumber(45))
        val lotto = Lotto(lottoNumbers)
        val winningLotto = WinningLotto(winningNumbers1to6, bonusNumber45)
        assertThat(winningLotto.getRank(lotto)).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `일치하는 번호가 5개이고 보너스 번호가 일치하지 않으면 등수는 3등이다`() {
        val lottoNumbers = (1..5).map { LottoNumber(it) }.toMutableSet()
        lottoNumbers.add(LottoNumber(7))
        val lotto = Lotto(lottoNumbers)
        val winningLotto = WinningLotto(winningNumbers1to6, bonusNumber45)
        assertThat(winningLotto.getRank(lotto)).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `일치하는 번호가 2개 이하이면 등수는 MISS이다`() {
        val lottoNumbers = (5..10).map { LottoNumber(it) }.toSet()
        val lotto = Lotto(lottoNumbers)
        val winningLotto = WinningLotto(winningNumbers1to6, bonusNumber45)
        assertThat(winningLotto.getRank(lotto)).isEqualTo(Rank.MISS)
    }
}
