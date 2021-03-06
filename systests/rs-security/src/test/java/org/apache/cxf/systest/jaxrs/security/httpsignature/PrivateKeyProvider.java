/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.cxf.systest.jaxrs.security.httpsignature;

import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.cxf.common.classloader.ClassLoaderUtils;

/**
 * Just a test-class to provide a static method to easily load a PrivateKey in spring config.
 */
public final class PrivateKeyProvider {

    private PrivateKeyProvider() {
        // complete
    }

    public static PrivateKey loadPrivateKey() {
        KeyStore keyStore;
        try {
            keyStore = KeyStore.getInstance("JKS");
            keyStore.load(ClassLoaderUtils.getResourceAsStream("keys/bob.jks", PrivateKeyProvider.class),
                          "password".toCharArray());
            return (PrivateKey)keyStore.getKey("bob", "password".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException
            | IOException | UnrecoverableKeyException e) {
            e.printStackTrace();
            return null;
        }
    }
}
