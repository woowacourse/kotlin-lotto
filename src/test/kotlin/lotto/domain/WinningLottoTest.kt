package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WinningLottoTest {
    @Test
    fun `메인 번호는 6개이다`() {
        assertThrows<IllegalArgumentException> {
            WinningLotto(
                listOf<LottoNumber>(
                    LottoNumber(3),
                    LottoNumber(45),
                    LottoNumber(34),
                ),
                LottoNumber(36),
            )
        }
    }

    @Test
    fun `보너스 번호를 가진다`() {
        assertThat(
            WinningLotto(
                listOf<LottoNumber>(
                    LottoNumber(3),
                    LottoNumber(45),
                    LottoNumber(34),
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(4),
                ),
                LottoNumber(10),
            )
                .bonusLottoNumber.value,
        ).isEqualTo(10)
    }

    @MethodSource("provideDuplicateNumbers")
    @ParameterizedTest
    fun `메인과 보너스 번호를 포함한 모든 번호는 서로 중복되지 않는다`(mainLottoNumbers: List<LottoNumber>, bonusLottoNumber: LottoNumber) {
        assertThrows<IllegalArgumentException> {
            WinningLotto(mainLottoNumbers, bonusLottoNumber)
        }
    }

    companion object {
        @JvmStatic
        fun provideDuplicateNumbers() = listOf(
            Arguments.of(
                listOf<LottoNumber>(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(5),
                ),
                LottoNumber(6),
            ),
            Arguments.of(
                listOf<LottoNumber>(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(6),
                ),
                LottoNumber(6),
            ),
        )
    }
}
