package lotto.model

import lotto.domain.model.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningLottoTest {
    @Test
    fun `당첨 로또 번호와 보너스 번호가 중복이면 null을 반환한다`() {
        val winningLotto = WinningLotto.valueOfOrNull(
            createLottoNumbers(listOf(1, 2, 3, 4, 5, 6)), LottoNumber.valueOf(6)
        )
        assertThat(winningLotto).isNull()
    }

    @Test
    fun `당첨 로또와 비교하여 로또 순위를 반환한다`() {
        val winningLotto = WinningLotto.valueOfOrNull(
            createLottoNumbers(listOf(1, 2, 3, 4, 5, 6)), LottoNumber.valueOf(7)
        ) ?: throw IllegalStateException("WinningLotto 생성 실패")

        val rank = winningLotto.findRank(
            when (val result = Lotto.valueOf(createLottoNumbers(listOf(1, 2, 3, 4, 5, 6)))) {
                is LottoCreationResult.Success -> result.lotto
                is LottoCreationResult.Failure -> throw IllegalArgumentException("Lotto 생성 실패")
            }
        )

        assertThat(rank).isEqualTo(Rank.FIRST)
    }



    private fun createLottoNumbers(numberList: List<Int>): List<LottoNumber> =
        numberList.map { LottoNumber.valueOf(it) }
}
