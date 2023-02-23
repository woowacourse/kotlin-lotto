package lotto.domain

import lotto.constant.Rank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WinningLottoTest {
    @Test
    fun `메인 번호는 6개이다`() {
        assertThrows<IllegalArgumentException> {
            WinningLotto(
                setOf<LottoNumber>(
                    LottoNumber.from(3),
                    LottoNumber.from(45),
                    LottoNumber.from(34),
                ),
                LottoNumber.from(36),
            )
        }
    }

    @MethodSource("provideDuplicateNumbers")
    @ParameterizedTest
    fun `메인과 보너스 번호를 포함한 모든 번호는 서로 중복되지 않는다`(mainLottoNumbers: Set<LottoNumber>, bonusLottoNumber: LottoNumber) {
        assertThrows<IllegalArgumentException> {
            WinningLotto(mainLottoNumbers, bonusLottoNumber)
        }
    }

    @Test
    fun `메인 번호가 5개 맞고 보너스 번호가 맞으면 2등이다`() {
        val winningLotto = WinningLotto(
            setOf<LottoNumber>(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6),
            ),
            LottoNumber.from(7),
        )

        val lotto = Lotto(
            setOf<LottoNumber>(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(7),
            ),
        )

        assertThat(winningLotto.getRank(lotto)).isEqualTo(Rank.SECOND)
    }

    companion object {
        @JvmStatic
        fun provideDuplicateNumbers() = listOf(
            Arguments.of(
                setOf<LottoNumber>(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(5),
                ),
                LottoNumber.from(6),
            ),
            Arguments.of(
                setOf<LottoNumber>(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6),
                ),
                LottoNumber.from(6),
            ),
        )
    }
}
