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

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;

import com.emv.qrcode.core.model.cpm.BERTemplate;
import com.emv.qrcode.model.cpm.ApplicationTemplate;
import com.emv.qrcode.model.cpm.CommonDataTemplate;
import com.emv.qrcode.model.cpm.ConsumerPresentedMode;
import com.emv.qrcode.model.cpm.PayloadFormatIndicator;

/**
 * Builder for constructing {@link ConsumerPresentedMode} QR code data using a fluent API.
 * This builder provides a convenient way to assemble EMV CPM QR code data structures
 * with PayloadFormatIndicator, ApplicationTemplates, and CommonDataTemplate.
 *
 * <p>Usage example:</p>
 * <pre>{@code
 * ConsumerPresentedMode cpm = ConsumerPresentedModeBuilder.newBuilder()
 *     .withPayloadFormatIndicator()
 *     .addApplicationTemplate(template -> template
 *         .withApplicationDefinitionFileName("A0000000555555")
 *         .withApplicationLabel("Product1"))
 *     .withCommonDataTemplate(template -> template
 *         .withApplicationPAN("1234567890123458")
 *         .withCardholderName("CARDHOLDER/EMV"))
 *     .build();
 *
 * String hex = cpm.toHex();
 * String base64 = cpm.toBase64();
 * }</pre>
 *
 * @see ConsumerPresentedMode
 * @see ApplicationTemplateBuilder
 * @see CommonDataTemplateBuilder
 * @since EMVCo QR Code Specification v1.0
 */
public final class ConsumerPresentedModeBuilder {

  private final ConsumerPresentedMode instance;

  private ConsumerPresentedModeBuilder() {
    this.instance = new ConsumerPresentedMode();
  }

  /**
   * Creates a new builder instance.
   *
   * @return a new ConsumerPresentedModeBuilder
   */
  public static ConsumerPresentedModeBuilder newBuilder() {
    return new ConsumerPresentedModeBuilder();
  }

  /**
   * Sets the Payload Format Indicator with the default value "CPV01".
   *
   * @return this builder
   */
  public ConsumerPresentedModeBuilder withPayloadFormatIndicator() {
    this.instance.setPayloadFormatIndicator(new PayloadFormatIndicator());
    return this;
  }

  /**
   * Sets a custom Payload Format Indicator.
   *
   * @param payloadFormatIndicator the payload format indicator to set
   * @return this builder
   */
  public ConsumerPresentedModeBuilder withPayloadFormatIndicator(final PayloadFormatIndicator payloadFormatIndicator) {
    this.instance.setPayloadFormatIndicator(payloadFormatIndicator);
    return this;
  }

  /**
   * Adds an Application Template using a fluent builder consumer.
   *
   * @param consumer a consumer that configures the ApplicationTemplateBuilder
   * @return this builder
   */
  public ConsumerPresentedModeBuilder addApplicationTemplate(final Consumer<ApplicationTemplateBuilder> consumer) {
    final ApplicationTemplateBuilder builder = ApplicationTemplateBuilder.newBuilder();
    consumer.accept(builder);
    this.instance.addApplicationTemplate(builder.build());
    return this;
  }

  /**
   * Adds a pre-built Application Template.
   *
   * @param applicationTemplate the application template to add
   * @return this builder
   */
  public ConsumerPresentedModeBuilder addApplicationTemplate(final ApplicationTemplate applicationTemplate) {
    this.instance.addApplicationTemplate(applicationTemplate);
    return this;
  }

  /**
   * Sets the Common Data Template using a fluent builder consumer.
   *
   * @param consumer a consumer that configures the CommonDataTemplateBuilder
   * @return this builder
   */
  public ConsumerPresentedModeBuilder withCommonDataTemplate(final Consumer<CommonDataTemplateBuilder> consumer) {
    final CommonDataTemplateBuilder builder = CommonDataTemplateBuilder.newBuilder();
    consumer.accept(builder);
    this.instance.setCommonDataTemplate(builder.build());
    return this;
  }

  /**
   * Sets a pre-built Common Data Template.
   *
   * @param commonDataTemplate the common data template to set
   * @return this builder
   */
  public ConsumerPresentedModeBuilder withCommonDataTemplate(final CommonDataTemplate commonDataTemplate) {
    this.instance.setCommonDataTemplate(commonDataTemplate);
    return this;
  }

  /**
   * Adds an additional (other) template.
   *
   * @param otherTemplate the template to add
   * @return this builder
   */
  public ConsumerPresentedModeBuilder addOtherTemplate(final BERTemplate<byte[]> otherTemplate) {
    if (Objects.nonNull(otherTemplate)) {
      this.instance.addOtherTemplate(otherTemplate);
    }
    return this;
  }

  /**
   * Builds and returns the configured ConsumerPresentedMode instance.
   *
   * @return the configured ConsumerPresentedMode
   */
  public ConsumerPresentedMode build() {
    return this.instance;
  }

  /**
   * Builds the ConsumerPresentedMode and returns its BER-TLV byte array representation.
   *
   * @return the BER-TLV encoded byte array
   * @throws IOException if an I/O error occurs during serialization
   */
  public byte[] toByteArray() throws IOException {
    return build().getBytes();
  }

  /**
   * Builds the ConsumerPresentedMode and returns its Base64-encoded string representation.
   *
   * @return the Base64-encoded string
   * @throws IOException if an I/O error occurs during serialization
   */
  public String toBase64() throws IOException {
    return build().toBase64();
  }

  /**
   * Builds the ConsumerPresentedMode and returns its hexadecimal string representation.
   *
   * @return the hexadecimal-encoded string
   * @throws IOException if an I/O error occurs during serialization
   */
  public String toHex() throws IOException {
    return build().toHex();
  }

}
