package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserLottoTest {
    @Test
    fun `현재 사용자가 가지고 있는 로또가 몇등인지 판단한다`() {
        val lotto = listOf(
            Lotto(
                listOf(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(9),
                    LottoNumber.from(10),
                    LottoNumber.from(11)
                )
            ),
            Lotto(
                listOf(
                    LottoNumber.from(7),
                    LottoNumber.from(8),
                    LottoNumber.from(9),
                    LottoNumber.from(10),
                    LottoNumber.from(11),
                    LottoNumber.from(12)
                )
            )
        )
        val userLotto = UserLotto(lotto)
        assertThat(
            userLotto.calculateTotalRank(
                WinningNumbers(
                    Lotto(
                        listOf(
                            LottoNumber.from(1),
                            LottoNumber.from(2),
                            LottoNumber.from(3),
                            LottoNumber.from(4),
                            LottoNumber.from(5),
                            LottoNumber.from(6)
                        )
                    ),
                    LottoNumber.from(7)
                )
            )
        ).isEqualTo(
            listOf(
                Rank.FIFTH, Rank.MISS
            )
        )
    }
}
