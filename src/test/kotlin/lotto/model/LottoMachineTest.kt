package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class LottoMachineTest {
    @Test
    fun `수동 로또를 추가하면 현재 로또 리스트와 남은 횟수에 반영된다`() {
        val manualLottoNumbers =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(2, 3, 4, 7, 8, 9),
                listOf(11, 12, 13, 15, 16, 17),
            )
        lottoMachine.addManualLotteries(manualLottoNumbers)
        val expectedCurrentLotteries =
            listOf(
                Lotto(1, 2, 3, 4, 5, 6),
                Lotto(2, 3, 4, 7, 8, 9),
                Lotto(11, 12, 13, 15, 16, 17),
            )

        assertAll(
            { assertThat(lottoMachine.currentLotteries).isEqualTo(expectedCurrentLotteries) },
            { assertThat(lottoMachine.availableCount.value).isEqualTo(7) },
        )
    }

    @Test
    fun `기존에 현재 로또 리스트에 존재하던 수동 로또들이 유지된 상태에서, 자동 로또들이 추가된다`() {
        val autoLottoNumbers =
            listOf(
                listOf(3, 5, 6, 7, 9, 10),
                listOf(21, 22, 23, 24, 25, 27),
                listOf(31, 34, 35, 37, 38, 39),
            )
        lottoMachine.addAutoLotteries(autoLottoNumbers)
        val expectedCurrentLotteries =
            listOf(
                Lotto(1, 2, 3, 4, 5, 6),
                Lotto(2, 3, 4, 7, 8, 9),
                Lotto(11, 12, 13, 15, 16, 17),
                Lotto(3, 5, 6, 7, 9, 10),
                Lotto(21, 22, 23, 24, 25, 27),
                Lotto(31, 34, 35, 37, 38, 39),
            )
        assertThat(lottoMachine.currentLotteries).isEqualTo(expectedCurrentLotteries)
    }

    @Test
    fun `우승 로또 정수 리스트로 우승 로또 객체를 설정할 수 있다`() {
        val winningNumbers = listOf(1, 2, 3, 4, 5, 9)
        lottoMachine.setWinningLotto(winningNumbers)
        assertThat(lottoMachine.winningLotto).isEqualTo(Lotto(1, 2, 3, 4, 5, 9))
    }

    companion object {
        private lateinit var lottoMachine: LottoMachine

        @JvmStatic
        @BeforeAll
        fun setUp() {
            lottoMachine = LottoMachine(Money(10000))
        }
    }
}
