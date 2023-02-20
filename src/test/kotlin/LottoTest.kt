import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class LottoTest {
    @Test
    fun `로또 번호의 개수가 6개가 아니라면 예외를 발생한다`() {
        val exception = assertThrows<IllegalArgumentException> {
            Lotto(
                listOf(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6),
                    LottoNumber.from(7),
                ),
            )
        }
        assertEquals(Lotto.LOTTO_NUMBERS_COUNT_ERROR, exception.message)
    }

    @Test
    fun `로또가 중복된 로또 번호를 가지고 있으면 예외를 발생한다`() {
        val exception = assertThrows<IllegalArgumentException> {
            Lotto(
                listOf(
                    LottoNumber.from(1),
                    LottoNumber.from(1),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6),
                ),
            )
        }
        assertEquals(Lotto.LOTTO_NUMBER_DUPLICATED_ERROR, exception.message)
    }

    @Test
    fun `(1,2,3,4,5,6)인 로또와 (1,2,3,4,5,7)인 당첨번호를 비교하면 일치하는 개수 5를 반환한다`() {
        // given
        val lotto = Lotto(
            listOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6),
            ),
        )

        val winningLotto = Lotto(
            listOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(7),
            ),
        )

        // when
        val actual = lotto.getCountOfMatch(winningLotto)
        val expected = 5

        // then
        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @CsvSource("1,true", "10,false")
    fun `보너스 번호를 로또가 가지고 있다면 true, 아니라면 false를 반환한다`(number: Int, expected: Boolean) {
        // given
        val lotto = Lotto(
            listOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6),
            ),
        )

        val bonusNumber = LottoNumber.from(number)

        // when
        val actual = lotto.isContainBonusNumber(bonusNumber)

        // then
        assertEquals(expected, actual)
    }
}
