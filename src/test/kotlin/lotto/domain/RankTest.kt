package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RankTest {
    @Test
    fun `일치하는 개수와 보너스번호 매치 여부를 알려주면 등수를 알려준다`() {
        assertThat(Rank.valueOf(5, true)).isEqualTo(Rank.SECOND)
    }
}
