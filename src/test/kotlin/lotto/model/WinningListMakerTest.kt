package lotto.model

import lotto.domain.model.Lotto
import lotto.domain.model.LottoCreationResult
import lotto.domain.model.LottoNumber
import lotto.domain.model.Rank
import lotto.domain.model.WinningLotto
import lotto.domain.service.WinningListMaker
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningListMakerTest {
    @Test
    fun `로또 리스트와 당첨 로또를 비교하여 순위 리스트를 반환한다`() {
        val lottoList = listOf(createLotto(listOf(1, 2, 3, 4, 5, 6)), createLotto(listOf(1, 2, 3, 4, 5, 7)))
        val winningLotto =
            WinningLotto.valueOfOrNull(
                createLottoNumbers(listOf(1, 2, 3, 4, 5, 6)),
                LottoNumber.valueOf(7),
            ) ?: throw IllegalStateException("WinningLotto 생성 실패")

        val winningListMaker = WinningListMaker(winningLotto)
        val result = winningListMaker.makeWinningList(lottoList)

        assertThat(result[Rank.FIRST]).isEqualTo(1)
        assertThat(result[Rank.SECOND]).isEqualTo(1)
    }

    private fun createLotto(numberList: List<Int>): Lotto =
        when (val result = Lotto.valueOf(numberList.map { LottoNumber.valueOf(it) })) {
            is LottoCreationResult.Success -> result.lotto
            is LottoCreationResult.Failure -> throw IllegalArgumentException("Lotto 생성 실패")
        }

    private fun createLottoNumbers(numberList: List<Int>): List<LottoNumber> = numberList.map { LottoNumber.valueOf(it) }
}
