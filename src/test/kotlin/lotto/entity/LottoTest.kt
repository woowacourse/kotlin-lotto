package lotto.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    @Test
    fun `갖고 있는 로또와 당첨 번호 6개가 모두 같으면 6을 반환한다`() {
        val lotto = Lotto(
            setOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6)
            )
        )
        val winLotto = WinLotto(
            Lotto(
                setOf(
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
        assertThat(lotto.determineCountOfMatch(winLotto.winNumber)).isEqualTo(6)
    }

    @Test
    fun `갖고 있는 로또와 당첨 보너스 번호가 같으면 true를 반환한다`() {
        val lotto = Lotto(
            setOf(
                LottoNumber(1),
                LottoNumber(2),
                LottoNumber(3),
                LottoNumber(4),
                LottoNumber(5),
                LottoNumber(6)
            )
        )
        val winLotto = WinLotto(
            Lotto(
                setOf(
                    LottoNumber(11),
                    LottoNumber(12),
                    LottoNumber(13),
                    LottoNumber(14),
                    LottoNumber(15),
                    LottoNumber(16)
                )
            ),
            LottoNumber(1)
        )
        assertThat(lotto.determineMatchBonus(winLotto.bonus)).isEqualTo(true)
    }

    @MethodSource("provideLottoCountNotSix")
    @ParameterizedTest
    fun `로또 번호가 6개가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(
                setOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5)
                )
            )
        }
    }

    @MethodSource("provideDuplicateLotto")
    @ParameterizedTest
    fun `로또 번호가 중복되면 예외가 발생한다`(lotto: Set<LottoNumber>) {
        assertThrows<IllegalArgumentException> { Lotto(lotto) }
    }

    companion object {
        @JvmStatic
        fun provideDuplicateLotto(): List<Arguments> {
            return listOf(
                Arguments.of(
                    setOf(
                        LottoNumber(1),
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5)
                    )
                ),
                Arguments.of(
                    setOf(
                        LottoNumber(41),
                        LottoNumber(41),
                        LottoNumber(42),
                        LottoNumber(43),
                        LottoNumber(44),
                        LottoNumber(45)
                    )
                )
            )
        }

        @JvmStatic
        fun provideLottoCountNotSix(): List<Arguments> {
            return listOf(
                Arguments.of(
                    setOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5)
                    )
                ),
                Arguments.of(
                    setOf(
                        LottoNumber(1),
                        LottoNumber(2),
                        LottoNumber(3),
                        LottoNumber(4),
                        LottoNumber(5),
                        LottoNumber(6),
                        LottoNumber(7)
                    )
                )
            )
        }
    }
}
