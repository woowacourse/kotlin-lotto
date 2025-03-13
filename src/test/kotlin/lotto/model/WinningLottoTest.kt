package lotto.model

import lotto.domain.model.Lotto
import lotto.domain.model.LottoCreationResult
import lotto.domain.model.LottoNumber
import lotto.domain.model.Rank
import lotto.domain.model.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WinningLottoTest {
    @Test
    fun `당첨 로또 번호와 보너스 번호가 중복이면 null을 반환한다`() {
        val winningLotto =
            WinningLotto.valueOfOrNull(
                createLottoNumbers(listOf(1, 2, 3, 4, 5, 6)),
                LottoNumber.valueOf(6),
            )
        assertThat(winningLotto).isNull()
    }

    @Test
    fun `당첨 로또와 비교하여 모두 일치하면 1등를 반환한다`() {
        val winningLotto =
            WinningLotto.valueOfOrNull(
                createLottoNumbers(listOf(1, 2, 3, 4, 5, 6)),
                LottoNumber.valueOf(7),
            ) ?: throw IllegalStateException("WinningLotto 생성 실패")

        val rank =
            winningLotto.findRank(
                when (val result = Lotto.valueOf(createLottoNumbers(listOf(1, 2, 3, 4, 5, 6)))) {
                    is LottoCreationResult.Success -> result.lotto
                    is LottoCreationResult.Failure -> throw IllegalArgumentException("Lotto 생성 실패")
                },
            )

        assertThat(rank).isEqualTo(Rank.FIRST)
    }

    @Test
    fun `당첨 로또와 비교하여 5개 일치하고, 보너스 번호가 일치하면 2등를 반환한다`() {
        val winningLotto =
            WinningLotto.valueOfOrNull(
                createLottoNumbers(listOf(1, 2, 3, 4, 5, 6)),
                LottoNumber.valueOf(7),
            ) ?: throw IllegalStateException("WinningLotto 생성 실패")

        val rank =
            winningLotto.findRank(
                when (val result = Lotto.valueOf(createLottoNumbers(listOf(1, 2, 3, 4, 5, 7)))) {
                    is LottoCreationResult.Success -> result.lotto
                    is LottoCreationResult.Failure -> throw IllegalArgumentException("Lotto 생성 실패")
                },
            )

        assertThat(rank).isEqualTo(Rank.SECOND)
    }

    @Test
    fun `당첨 로또와 비교하여 5개 일치하면 3등를 반환한다`() {
        val winningLotto =
            WinningLotto.valueOfOrNull(
                createLottoNumbers(listOf(1, 2, 3, 4, 5, 6)),
                LottoNumber.valueOf(7),
            ) ?: throw IllegalStateException("WinningLotto 생성 실패")

        val rank =
            winningLotto.findRank(
                when (val result = Lotto.valueOf(createLottoNumbers(listOf(1, 2, 3, 4, 5, 8)))) {
                    is LottoCreationResult.Success -> result.lotto
                    is LottoCreationResult.Failure -> throw IllegalArgumentException("Lotto 생성 실패")
                },
            )

        assertThat(rank).isEqualTo(Rank.THIRD)
    }

    @Test
    fun `당첨 로또와 비교하여 4개 일치하면 4등를 반환한다`() {
        val winningLotto =
            WinningLotto.valueOfOrNull(
                createLottoNumbers(listOf(1, 2, 3, 4, 5, 6)),
                LottoNumber.valueOf(7),
            ) ?: throw IllegalStateException("WinningLotto 생성 실패")

        val rank =
            winningLotto.findRank(
                when (val result = Lotto.valueOf(createLottoNumbers(listOf(1, 2, 3, 4, 8, 9)))) {
                    is LottoCreationResult.Success -> result.lotto
                    is LottoCreationResult.Failure -> throw IllegalArgumentException("Lotto 생성 실패")
                },
            )

        assertThat(rank).isEqualTo(Rank.FOURTH)
    }

    @Test
    fun `당첨 로또와 비교하여 3개 일치하면 5등를 반환한다`() {
        val winningLotto =
            WinningLotto.valueOfOrNull(
                createLottoNumbers(listOf(1, 2, 3, 4, 5, 6)),
                LottoNumber.valueOf(7),
            ) ?: throw IllegalStateException("WinningLotto 생성 실패")

        val rank =
            winningLotto.findRank(
                when (val result = Lotto.valueOf(createLottoNumbers(listOf(1, 2, 3, 8, 9, 10)))) {
                    is LottoCreationResult.Success -> result.lotto
                    is LottoCreationResult.Failure -> throw IllegalArgumentException("Lotto 생성 실패")
                },
            )

        assertThat(rank).isEqualTo(Rank.FIFTH)
    }

    @Test
    fun `당첨 로또와 비교하여 2개 이하 일치하면 꽝을 반환한다`() {
        val winningLotto =
            WinningLotto.valueOfOrNull(
                createLottoNumbers(listOf(1, 2, 3, 4, 5, 6)),
                LottoNumber.valueOf(7),
            ) ?: throw IllegalStateException("WinningLotto 생성 실패")

        val rank =
            winningLotto.findRank(
                when (val result = Lotto.valueOf(createLottoNumbers(listOf(1, 2, 8, 9, 10, 11)))) {
                    is LottoCreationResult.Success -> result.lotto
                    is LottoCreationResult.Failure -> throw IllegalArgumentException("Lotto 생성 실패")
                },
            )

        assertThat(rank).isEqualTo(Rank.MISS)
    }

    private fun createLottoNumbers(numberList: List<Int>): List<LottoNumber> = numberList.map { LottoNumber.valueOf(it) }
}
