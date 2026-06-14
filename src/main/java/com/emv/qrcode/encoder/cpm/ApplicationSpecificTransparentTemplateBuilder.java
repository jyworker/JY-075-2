/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.emv.qrcode.encoder.cpm;

import java.util.Objects;

import com.emv.qrcode.core.model.cpm.BERTLV;
import com.emv.qrcode.model.cpm.ApplicationSpecificTransparentTemplate;

/**
 * Builder for constructing {@link ApplicationSpecificTransparentTemplate} instances
 * using a fluent API. This template contains transparent (non-interpreted)
 * application-specific data elements.
 *
 * <p>Usage example:</p>
 * <pre>{@code
 * ApplicationSpecificTransparentTemplate template =
 *     ApplicationSpecificTransparentTemplateBuilder.newBuilder()
 *         .withIssuerApplicationData("06010A03000000")
 *         .withApplicationCryptogram("584FD385FA234BCC")
 *         .build();
 * }</pre>
 *
 * @see ApplicationSpecificTransparentTemplate
 * @since EMVCo QR Code Specification v1.0
 */
public final class ApplicationSpecificTransparentTemplateBuilder {

  private final ApplicationSpecificTransparentTemplate instance;

  private ApplicationSpecificTransparentTemplateBuilder() {
    this.instance = new ApplicationSpecificTransparentTemplate();
  }

  /**
   * Creates a new builder instance.
   *
   * @return a new ApplicationSpecificTransparentTemplateBuilder
   */
  public static ApplicationSpecificTransparentTemplateBuilder newBuilder() {
    return new ApplicationSpecificTransparentTemplateBuilder();
  }

  /**
   * Sets the Application Definition File Name (tag 4F).
   *
   * @param value the hex-encoded ADF name
   * @return this builder
   */
  public ApplicationSpecificTransparentTemplateBuilder withApplicationDefinitionFileName(final String value) {
    this.instance.setApplicationDefinitionFileName(value);
    return this;
  }

  /**
   * Sets the Application Label (tag 50).
   *
   * @param value the application label
   * @return this builder
   */
  public ApplicationSpecificTransparentTemplateBuilder withApplicationLabel(final String value) {
    this.instance.setApplicationLabel(value);
    return this;
  }

  /**
   * Sets the Track 2 Equivalent Data (tag 57).
   *
   * @param value the hex-encoded track 2 data
   * @return this builder
   */
  public ApplicationSpecificTransparentTemplateBuilder withTrack2EquivalentData(final String value) {
    this.instance.setTrack2EquivalentData(value);
    return this;
  }

  /**
   * Sets the Application PAN (tag 5A).
   *
   * @param value the compressed numeric PAN
   * @return this builder
   */
  public ApplicationSpecificTransparentTemplateBuilder withApplicationPAN(final String value) {
    this.instance.setApplicationPAN(value);
    return this;
  }

  /**
   * Sets the Cardholder Name (tag 5F20).
   *
   * @param value the cardholder name
   * @return this builder
   */
  public ApplicationSpecificTransparentTemplateBuilder withCardholderName(final String value) {
    this.instance.setCardholderName(value);
    return this;
  }

  /**
   * Sets the Language Preference (tag 5F2D).
   *
   * @param value the language preference (ISO 639-1 codes)
   * @return this builder
   */
  public ApplicationSpecificTransparentTemplateBuilder withLanguagePreference(final String value) {
    this.instance.setLanguagePreference(value);
    return this;
  }

  /**
   * Sets the Issuer URL (tag 5F50).
   *
   * @param value the issuer URL
   * @return this builder
   */
  public ApplicationSpecificTransparentTemplateBuilder withIssuerURL(final String value) {
    this.instance.setIssuerURL(value);
    return this;
  }

  /**
   * Sets the Application Version Number (tag 9F08).
   *
   * @param value the hex-encoded version number
   * @return this builder
   */
  public ApplicationSpecificTransparentTemplateBuilder withApplicationVersionNumber(final String value) {
    this.instance.setApplicationVersionNumber(value);
    return this;
  }

  /**
   * Sets the Token Requestor ID (tag 9F19).
   *
   * @param value the numeric token requestor ID
   * @return this builder
   */
  public ApplicationSpecificTransparentTemplateBuilder withTokenRequestorID(final String value) {
    this.instance.setTokenRequestorID(value);
    return this;
  }

  /**
   * Sets the Payment Account Reference (tag 9F24).
   *
   * @param value the payment account reference
   * @return this builder
   */
  public ApplicationSpecificTransparentTemplateBuilder withPaymentAccountReference(final String value) {
    this.instance.setPaymentAccountReference(value);
    return this;
  }

  /**
   * Sets the Last 4 Digits of PAN (tag 9F25).
   *
   * @param value the numeric last 4 digits
   * @return this builder
   */
  public ApplicationSpecificTransparentTemplateBuilder withLast4DigitsOfPAN(final String value) {
    this.instance.setLast4DigitsOfPAN(value);
    return this;
  }

  /**
   * Sets the Cryptogram Information Data (tag 9F27).
   *
   * @param value the hex-encoded CID
   * @return this builder
   */
  public ApplicationSpecificTransparentTemplateBuilder withCryptogramInformationData(final String value) {
    this.instance.setCryptogramInformationData(value);
    return this;
  }

  /**
   * Sets the Application Transaction Counter (tag 9F36).
   *
   * @param value the numeric ATC
   * @return this builder
   */
  public ApplicationSpecificTransparentTemplateBuilder withApplicationTransactionCounter(final String value) {
    this.instance.setApplicationTransactionCounter(value);
    return this;
  }

  /**
   * Sets the Application Cryptogram (tag 9F26).
   *
   * @param value the hex-encoded AC
   * @return this builder
   */
  public ApplicationSpecificTransparentTemplateBuilder withApplicationCryptogram(final String value) {
    this.instance.setApplicationCryptogram(value);
    return this;
  }

  /**
   * Sets the Issuer Application Data (tag 9F10).
   *
   * @param value the hex-encoded IAD
   * @return this builder
   */
  public ApplicationSpecificTransparentTemplateBuilder withIssuerApplicationData(final String value) {
    this.instance.setIssuerApplicationData(value);
    return this;
  }

  /**
   * Sets the Unpredictable Number (tag 9F37).
   *
   * @param value the hex-encoded UN
   * @return this builder
   */
  public ApplicationSpecificTransparentTemplateBuilder withUnpredictableNumber(final String value) {
    this.instance.setUnpredictableNumber(value);
    return this;
  }

  /**
   * Adds a custom additional BER-TLV data element.
   *
   * @param bertlv the BER-TLV element to add
   * @return this builder
   */
  public ApplicationSpecificTransparentTemplateBuilder withAdditionalData(final BERTLV bertlv) {
    if (Objects.nonNull(bertlv)) {
      this.instance.addAdditionalData(bertlv);
    }
    return this;
  }

  /**
   * Builds and returns the configured ApplicationSpecificTransparentTemplate.
   *
   * @return the configured ApplicationSpecificTransparentTemplate
   */
  public ApplicationSpecificTransparentTemplate build() {
    return this.instance;
  }

}
