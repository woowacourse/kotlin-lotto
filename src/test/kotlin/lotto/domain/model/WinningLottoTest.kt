package lotto.domain.model

import lotto.domain.value.LottoNumber
import lotto.enums.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningLottoTest {
    private lateinit var winningNumbers: Lotto
    private val bonusNumber = LottoNumber(45)

    @BeforeEach
    fun setUp() {
        val lottoNumbers = (1..6).map { LottoNumber(it) }
        winningNumbers = Lotto(lottoNumbers)
    }

    @Test
    fun `당첨 번호와 보너스 번호는 중복되지 않는다`() {
        assertDoesNotThrow {
            WinningLotto(winningNumbers, bonusNumber)
        }
    }

    @Test
    fun `당첨 번호와 보너스 번호가 중복되면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            val bonusNumber = LottoNumber(1)
            WinningLotto(winningNumbers, bonusNumber)
        }
    }

    @Test
    fun `일치하는 번호가 6개이면 등수는 1등이다`() {
        val lottoNumbers = (1..6).map { LottoNumber(it) }
        val lotto = Lotto(lottoNumbers)
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)
        assertThat(winningLotto.getRank(lotto)).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `일치하는 번호가 5개이고 보너스 번호가 일치하면 등수는 2등이다`() {
        val lottoNumbers = (1..5).map { LottoNumber(it) }.toMutableList()
        lottoNumbers.add(LottoNumber(45))
        val lotto = Lotto(lottoNumbers)
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)
        assertThat(winningLotto.getRank(lotto)).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `일치하는 번호가 5개이고 보너스 번호가 일치하지 않으면 등수는 3등이다`() {
        val lottoNumbers = (2..7).map { LottoNumber(it) }
        val lotto = Lotto(lottoNumbers)
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)
        assertThat(winningLotto.getRank(lotto)).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `일치하는 번호가 4개이면 등수는 4등이다`() {
        val lottoNumbers = (3..8).map { LottoNumber(it) }
        val lotto = Lotto(lottoNumbers)
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)
        assertThat(winningLotto.getRank(lotto)).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `일치하는 번호가 3개이면 등수는 5등이다`() {
        val lottoNumbers = (4..9).map { LottoNumber(it) }
        val lotto = Lotto(lottoNumbers)
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)
        assertThat(winningLotto.getRank(lotto)).isEqualTo(Rank.FIFTH)
    }

    @Test
    fun `일치하는 번호가 2개 이하이면 등수는 MISS이다`() {
        val lottoNumbers = (5..10).map { LottoNumber(it) }
        val lotto = Lotto(lottoNumbers)
        val winningLotto = WinningLotto(winningNumbers, bonusNumber)
        assertThat(winningLotto.getRank(lotto)).isEqualTo(Rank.MISS)
    }
}
