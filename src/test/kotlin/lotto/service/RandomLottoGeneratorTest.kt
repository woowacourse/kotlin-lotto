package lotto.service

import lotto.domain.model.Lotto
import lotto.domain.service.RandomLottoGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RandomLottoGeneratorTest {
    @Test
    fun `로또 객체를 생성한다`() {
        // Given
        val lottoGenerator = RandomLottoGenerator()

        // When
        val lotto = lottoGenerator.generateLotto()

        // Then
        assertThat(lotto).isExactlyInstanceOf(Lotto::class.java)
    }
}
