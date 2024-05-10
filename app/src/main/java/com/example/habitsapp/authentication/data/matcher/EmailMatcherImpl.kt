package com.example.habitsapp.authentication.data.matcher

import android.util.Patterns
import com.example.habitsapp.authentication.domain.matcher.EmailMatcher

/**
 * Se crea esta clase ya que no deberiamos tener dependencias de android en nuestro domain, esto falicita los test unitarios asi como a su performance.
 *
 */
class EmailMatcherImpl : EmailMatcher {
    override fun isValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}