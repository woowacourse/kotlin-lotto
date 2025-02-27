package lotto.domain

import lotto.domain.model.Lotto
import lotto.domain.model.LottoWinningStats
import lotto.domain.model.Lottos
import lotto.domain.model.WinningLotto
import lotto.domain.value.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottosTest {
    private lateinit var winningLotto: WinningLotto
    private lateinit var lottosHasTwoTickets: Lottos

    @BeforeEach
    fun setUp() {
        val lottoWithoutBonus = Lotto.createManual(listOf(1, 2, 3, 4, 5, 6))
        val bonusNumber = LottoNumber(45)
        winningLotto = WinningLotto(lottoWithoutBonus, bonusNumber)

        val lottoNumbers = listOf(4, 5, 6, 7, 8, 9)
        lottosHasTwoTickets = Lottos(listOf(Lotto.createManual(lottoNumbers), Lotto.createManual(lottoNumbers)))
    }

    @Test
    fun `당첨 로또 인스턴스를 전달받아 당첨 통계 인스턴스를 반환한다`() {
        // Given
        val winningLotto = winningLotto
        val lottosHasTwoTickets = lottosHasTwoTickets

        // When
        val winningStats = lottosHasTwoTickets.getLottoWinningStats(winningLotto)

        // Then
        assertThat(winningStats).isExactlyInstanceOf(LottoWinningStats::class.java)
    }
}
