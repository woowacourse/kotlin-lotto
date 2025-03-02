package lottotest.domain.model

import lotto.domain.model.ManualLottoTicket
import lotto.domain.valueobject.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ManualLottoTicketTest {
    @ParameterizedTest
    @MethodSource("wrongSizeLottoNumbers")
    fun `로또 번호 컬렉션의 사이즈가 로또 티켓 한장의 사이즈와 다르다면 인스턴스를 생성하지 않는다`(numbers: List<Int>) {
        // given
        val exampleLottoNumbers: List<LottoNumber> = numbers.map { LottoNumber(it) }

        // when then
        assertThrows<IllegalArgumentException> {
            ManualLottoTicket(exampleLottoNumbers)
        }
    }

    @ParameterizedTest
    @MethodSource("duplicateLottoNumbers")
    fun `로또 번호 컬렉션에 중복된 로또 번호가 존재한다면 인스턴스를 생성하지 않는다`(numbers: List<Int>) {
        // given
        val exampleLottoNumbers: List<LottoNumber> = numbers.map { LottoNumber(it) }

        // when then
        assertThrows<IllegalArgumentException> {
            ManualLottoTicket(exampleLottoNumbers)
        }
    }

    @ParameterizedTest
    @MethodSource("normalLottoNumbers")
    fun `로또 번호 컬렉션의 사이즈가 로또 티켓 한장의 사이즈와 같다면 인스턴스는 입력한 로또 번호들을 가진다`(numbers: List<Int>) {
        // given
        val exampleLottoNumbers: List<LottoNumber> = numbers.map { LottoNumber(it) }

        // when
        val actual = ManualLottoTicket(exampleLottoNumbers).lottoNumbers.toSet()
        val expected = exampleLottoNumbers.toSet()

        // when then
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun wrongSizeLottoNumbers() =
            Stream.of(
                listOf(),
                listOf(1),
                listOf(1, 2, 3, 4, 5),
                listOf(1, 2, 3, 4, 5, 6, 7),
            )

        @JvmStatic
        fun duplicateLottoNumbers() =
            Stream.of(
                listOf(1, 2, 3, 4, 5, 1),
                listOf(9, 22, 34, 34, 25, 18),
            )

        @JvmStatic
        fun normalLottoNumbers() =
            Stream.of(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(9, 22, 36, 34, 25, 18),
            )
    }
}
