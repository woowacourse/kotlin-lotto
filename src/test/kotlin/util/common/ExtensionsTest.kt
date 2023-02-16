package util.common

import domain.lotto.number.LottoNumber
import io.kotest.inspectors.forAll
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class ExtensionsTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6])
    fun `정수형 범위가 주어졌을 때, generateDistinctRandomNumbers 호출시, 중복 되지 않는 숫자를 count만큼 반환한다`(count: Int) {
        val actual = (LottoNumber.MIN_LOTTO_NUMBER..LottoNumber.MAX_LOTTO_NUMBER)
            .generateDistinctRandomNumbers(count)
            .toSet().size

        Assertions.assertThat(count).isEqualTo(actual)
    }

    @Test
    fun `정수형 범위가 주어졌을 때, generateDistinctRandomNumbers 호출시, 주어진 범위에 속하는 정수 리스트를 반환한다`() {
        val actual = (LottoNumber.MIN_LOTTO_NUMBER..LottoNumber.MAX_LOTTO_NUMBER)
            .generateDistinctRandomNumbers(6)

        actual.forAll { randomNumber ->
            Assertions.assertThat(randomNumber).isBetween(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER)
        }
    }

    @ParameterizedTest
    @CsvSource(value = ["10000,10", "50000,50", "100000,100", "1450,1", "0,0", "-1000,0"])
    fun `정수가 주어졌을 때, divideByThousand 호출시, 1000으로 나눈 몫을 반환한다`(money: Int, expected: Int) {
        val actual = money.divideByThousand()

        assertEquals(actual, expected)
    }
}
