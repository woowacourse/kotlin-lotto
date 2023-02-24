package domain.lotto

import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import util.PREFIX

class LottoTest {
    @MethodSource("provideNumbers")
    @ParameterizedTest
    fun `로또 번호가 6개가 아니라면 에러 발생 (5개일때)`(numbers: List<Int>) {
        // given

        // when

        // then
        assertThrows<IllegalArgumentException> { Lotto(numbers) }
            .shouldHaveMessage("$PREFIX 로또번호는 6개여야합니다.")
    }

    companion object {
        @JvmStatic
        fun provideNumbers(): List<Arguments> = listOf(
            Arguments.of(listOf(1, 2, 3, 4, 5)),
            Arguments.of(listOf(1, 2, 3, 4, 5, 6, 7)),
        )
    }
}
