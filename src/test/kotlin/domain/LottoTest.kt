package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `랜덤 생성시 로또 번호를 6개 가진 로또 객체가 생성된다`() {
        // given
        val lottoNumbers = setOf(
            LottoNumber.of(1),
            LottoNumber.of(2),
            LottoNumber.of(3),
            LottoNumber.of(4),
            LottoNumber.of(5),
            LottoNumber.of(6)
        )

        // when
        val lotto = Lotto(lottoNumbers)

        // then
        assertThat(lotto.lottoNumbers).isEqualTo(lottoNumbers)
    }

    @Test
    fun `부생성자로 생성시 로또 번호를 6개 가진 로또 객체가 생성된다`() {
        // given
        val lottoNumbers = listOf("1", "2", "3", "4", "5", "6")

        // when
        val lotto = Lotto(lottoNumbers)

        // then
        assertThat(lotto.lottoNumbers).isEqualTo(
            setOf(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6),
            )
        )
    }

    @Test
    fun `로또 번호가 6개가 아니라면 에러 발생`() {
        // given
        val lottoNumbers = setOf(
            LottoNumber.of(1),
            LottoNumber.of(2),
            LottoNumber.of(3),
            LottoNumber.of(4),
            LottoNumber.of(5)
        )

        // when

        // then
        assertThatIllegalArgumentException().isThrownBy { Lotto(lottoNumbers) }
            .withMessageContaining("[Error] 로또번호는 서로 다른 숫자 6개여야합니다.")
    }

    @Test
    fun `로또 번호를 6개를 입력하나 중복이 있을 경우 에러발생`() {
        // given
        val lottoNumbers = listOf<String>("1", "2", "2", "3", "4", "5")

        // when
        val exception = assertThrows<IllegalArgumentException> { Lotto(lottoNumbers) }

        // then
        assertThat(exception.message).isEqualTo("[Error] 중복된 수가 있습니다.")
    }

    @Test
    fun `로또 번호가 중복된다면 에러 발생`() {
        // given
        val lottoNumbers = listOf<String>("1", "2", "3", "4", "5", "5", "6")

        // when

        // then
        assertThatIllegalArgumentException()
            .isThrownBy { Lotto(lottoNumbers) }
            .withMessageContaining("[Error] 중복된 수가 있습니다.")
    }

    @Test
    fun `로또에 숫자가 아닌 것이 들어오면 에러 발생`() {
        // given
        val lottoNumbers = listOf<String>("a", "2", "3", "4", "5", "6")

        // when
        val exception = assertThrows<NumberFormatException> { Lotto(lottoNumbers) }

        // then
        assertThat(exception.message).isEqualTo("[Error] 숫자로 입력해주세요.")
    }

    @Test
    fun `순서에 맞지 않게 로또 번호가 입력될 경우 정렬한다`() {
        // given
        val lottoNumbers = listOf<String>("1", "5", "3", "6", "4", "2")
        val expected = setOf(
            LottoNumber.of(1),
            LottoNumber.of(2),
            LottoNumber.of(3),
            LottoNumber.of(4),
            LottoNumber.of(5),
            LottoNumber.of(6)
        ).toList()

        // when
        val actual = Lotto(lottoNumbers).lottoNumbers.toList()

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
