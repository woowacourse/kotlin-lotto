package lotto.service

import lotto.domain.service.RandomLottoNumbersGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomLottoNumbersGeneratorTest {
    @Test
    fun `랜덤한 로또 번호 인스턴스들을 가지는 컬렉션을 생성한다`() {
        // Given
        val randomlottoNumbersGenerator = RandomLottoNumbersGenerator()
        val lottoNumbersA = randomlottoNumbersGenerator.generateLottoNumbers().sortedBy { it.number }
        val lottoNumbersB = randomlottoNumbersGenerator.generateLottoNumbers().sortedBy { it.number }

        // When
        val numbersAText = lottoNumbersA.map { it.number }.joinToString(",")
        val numbersBText = lottoNumbersB.map { it.number }.joinToString(",")

        // Then
        assertThat(numbersAText).isNotEqualTo(numbersBText)
    }
}
