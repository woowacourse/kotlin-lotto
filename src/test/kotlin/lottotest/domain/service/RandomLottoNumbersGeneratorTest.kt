package lottotest.domain.service

import lotto.domain.service.RandomLottoNumbersGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomLottoNumbersGeneratorTest {
    @Test
    fun `랜덤한 로또 번호 인스턴스들을 가지는 컬렉션을 생성한다`() {
        // Given
        val randomlottoNumbersGenerator = RandomLottoNumbersGenerator()

        // When
        val lottoNumbersA = randomlottoNumbersGenerator.generateLottoNumbers()
        val lottoNumbersB = randomlottoNumbersGenerator.generateLottoNumbers()

        // Then
        assertThat(lottoNumbersA).isNotEqualTo(lottoNumbersB)
    }
}
