import domain.Lotto
import domain.LottoNumber
import domain.Rank
import domain.WinningLotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {

    @MethodSource("produceLotto")
    @ParameterizedTest
    fun `로또가 6개의 숫자로 잘 생성되었는지 확인`(numbers: List<LottoNumber>) {
        assertDoesNotThrow {
            Lotto(numbers)
        }
    }

    @MethodSource("produceWrongSizeLotto")
    @ParameterizedTest
    fun `로또가 6개의 숫자로 이루어지지 않은 경우 예외가 발생한다`(numbers: List<LottoNumber>) {
        assertThat(
            assertThrows<IllegalArgumentException> {
                Lotto(numbers)
            }.message!!
        ).isEqualTo("당첨 번호가 6개가 아닙니다")
    }

    @MethodSource("produceOverlayLotto")
    @ParameterizedTest
    fun `중복된 번호가 존재하는 경우 예외가 발생한다`(numbers: List<LottoNumber>) {
        assertThat(
            assertThrows<IllegalArgumentException> {
                Lotto(numbers)
            }.message!!
        ).isEqualTo("당첨 번호가 중복되었습니다.")
    }

    /**
     * 생성된 로또 Lotto(1, 2, 3, 4, 5, 6)
     * 당첨 로또 Lotto(1, 2, 3, 7, 8, 9)
     */
    @MethodSource("produceFifthLotto")
    @ParameterizedTest
    fun `생성된 로또가 당첨 로또와 3개 일치하면 5등인지 확인`(lotto: Lotto, winningLotto: WinningLotto) {
        val actual = lotto.matchLotto(winningLotto)
        assertThat(actual).isEqualTo(Rank.FIFTH)
    }

    /**
     * 생성된 로또 Lotto(1, 2, 3, 4, 5, 6)
     * 당첨 로또 Lotto(1, 2, 3, 4, 8, 9)
     */
    @MethodSource("produceFourthLotto")
    @ParameterizedTest
    fun `생성된 로또가 당첨 로또와 4개 일치하면 4등인지 확인`(lotto: Lotto, winningLotto: WinningLotto) {
        val actual = lotto.matchLotto(winningLotto)
        assertThat(actual).isEqualTo(Rank.FOURTH)
    }

    /**
     * 생성된 로또 Lotto(1, 2, 3, 4, 5, 6)
     * 당첨 로또 Lotto(1, 2, 3, 4, 5, 9)
     * 보너스 번호 20
     */
    @MethodSource("produceThirdLotto")
    @ParameterizedTest
    fun `생성된 로또가 당첨 로또와 5개 일치하고 보너스 번호가 포함되지 않으면, 3등인지 확인`(lotto: Lotto, winningLotto: WinningLotto) {
        val actual = lotto.matchLotto(winningLotto)
        assertThat(actual).isEqualTo(Rank.THIRD)
    }

    /**
     * 생성된 로또 Lotto(1, 2, 3, 4, 5, 6)
     * 당첨 로또 Lotto(1, 2, 3, 4, 5, 9)
     * 보너스 번호 6
     */
    @MethodSource("produceSecondLotto")
    @ParameterizedTest
    fun `생성된 로또가 당첨 로또와 5개 일치하고 보너스 번호가 포함되면, 2등인지 확인`(lotto: Lotto, winningLotto: WinningLotto) {
        val actual = lotto.matchLotto(winningLotto)
        assertThat(actual).isEqualTo(Rank.SECOND)
    }

    /**
     * 생성된 로또 Lotto(1, 2, 3, 4, 5, 6)
     * 당첨 로또 Lotto(1, 2, 3, 4, 5, 6)
     */
    @MethodSource("produceFirstLotto")
    @ParameterizedTest
    fun `생성된 로또가 당첨 로또와 6개 일치하면, 1등인지 확인`(lotto: Lotto, winningLotto: WinningLotto) {
        val actual = lotto.matchLotto(winningLotto)
        assertThat(actual).isEqualTo(Rank.FIRST)
    }

    companion object {

        @JvmStatic
        fun produceLotto(): List<Arguments> {
            return listOf(
                Arguments.of(listOf(1, 3, 4, 6, 10, 22).map { LottoNumber.from(it) }),
            )
        }

        @JvmStatic
        fun produceWrongSizeLotto(): List<Arguments> {
            return listOf(
                Arguments.of((1..5).map { LottoNumber.from(it) }),
                Arguments.of((1..7).map { LottoNumber.from(it) }),
            )
        }

        @JvmStatic
        fun produceOverlayLotto(): List<Arguments> {
            return listOf(
                Arguments.of(listOf(1, 1, 2, 3, 4, 5).map { LottoNumber.from(it) }),
                Arguments.of(listOf(1, 10, 10, 20, 25, 33).map { LottoNumber.from(it) }),
            )
        }

        @JvmStatic
        fun produceFifthLotto(): List<Arguments> {
            return listOf(
                Arguments.of(
                    Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.from(it) }),
                    WinningLotto(listOf(1, 2, 3, 7, 8, 9).map { LottoNumber.from(it) }, LottoNumber.from(20))
                )
            )
        }

        @JvmStatic
        fun produceFourthLotto(): List<Arguments> {
            return listOf(
                Arguments.of(
                    Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.from(it) }),
                    WinningLotto(listOf(1, 2, 3, 4, 8, 9).map { LottoNumber.from(it) }, LottoNumber.from(20))
                )
            )
        }

        @JvmStatic
        fun produceThirdLotto(): List<Arguments> {
            return listOf(
                Arguments.of(
                    Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.from(it) }),
                    WinningLotto(listOf(1, 2, 3, 4, 5, 9).map { LottoNumber.from(it) }, LottoNumber.from(20))
                )
            )
        }

        @JvmStatic
        fun produceSecondLotto(): List<Arguments> {
            return listOf(
                Arguments.of(
                    Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.from(it) }),
                    WinningLotto(
                        listOf(1, 2, 3, 4, 5, 9).map { LottoNumber.from(it) }, LottoNumber.from(6)
                        )
                    )
                )
            }

            @JvmStatic
            fun produceFirstLotto(): List<Arguments> {
                return listOf(
                    Arguments.of(
                        Lotto(listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.from(it) }),
                        WinningLotto(
                            listOf(1, 2, 3, 4, 5, 6).map { LottoNumber.from(it) }, LottoNumber.from(10)
                            )
                        )
                    )
                }
            }
        }
        