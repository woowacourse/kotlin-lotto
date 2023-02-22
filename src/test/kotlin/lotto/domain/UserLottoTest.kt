package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserLottoTest {
    @Test
    fun `현재 사용자가 가지고 있는 로또가 몇등인지 판단한다`() {
        val lotto = listOf(
            Lotto(
                listOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(9),
                    LottoNumber(10),
                    LottoNumber(11)
                )
            ),
            Lotto(
                listOf(
                    LottoNumber(7),
                    LottoNumber(8),
                    LottoNumber(9),
                    LottoNumber(10),
                    LottoNumber(11),
                    LottoNumber(12)
                )
            )
        )
        val userLotto = UserLotto(2, lotto)
        assertThat(
            userLotto.calculateTotalRank(
                WinningNumbers(
                    Lotto(
                        listOf(
                            LottoNumber(1),
                            LottoNumber(2),
                            LottoNumber(3),
                            LottoNumber(4),
                            LottoNumber(5),
                            LottoNumber(6)
                        )
                    ),
                    LottoNumber(7)
                )
            )
        ).isEqualTo(
            listOf(
                Rank.FIFTH, Rank.MISS
            )
        )
    }
}
