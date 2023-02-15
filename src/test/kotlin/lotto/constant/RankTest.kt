package lotto.constant

import lotto.constant.BonusResult.ANY
import lotto.constant.BonusResult.BONUS_MATCH
import lotto.constant.BonusResult.BONUS_MISMATCH
import lotto.constant.Rank.FIRST
import lotto.constant.Rank.FOURTH
import lotto.constant.Rank.SECOND
import lotto.constant.Rank.THIRD
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `당첨 로또 번호 개수가 5개이고 보너스 번호가 매치되면 2등이다`() {
        assertThat(Rank.convertToGrade(5, BONUS_MATCH)).isEqualTo(SECOND)
    }

    @Test
    fun `당첨 로또 번호 개수가 5개이고 보너스 번호가 매치되지 않으면 3등이다`() {
        assertThat(Rank.convertToGrade(5, BONUS_MISMATCH)).isEqualTo(THIRD)
    }

    @Test
    fun `당첨 로또 번호 개수가 6개면 보너스 번호에 상관없이 1등이다`() {
        assertThat(Rank.convertToGrade(6, ANY)).isEqualTo(FIRST)
    }

    @Test
    fun `당첨 로또 번호 개수가 4개면 보너스 번호에 상관없이 4등이다`() {
        assertThat(Rank.convertToGrade(4, ANY)).isEqualTo(FOURTH)
    }
}
