/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.qlong;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import me.instrument.agent.Agent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * @author qilong.zql 18/6/12-下午8:48
 */
@SpringBootApplication
public class AppOneApplication {
    public static void main(String[] args) throws IOException, AgentLoadException, AgentInitializationException, AttachNotSupportedException {

        //获取Agent的jar包路径
        ProtectionDomain agentDomain = Agent.class.getProtectionDomain();
        CodeSource codeSourde = agentDomain.getCodeSource();
        String agentJarPath = new File(codeSourde.getLocation().getPath()).getAbsolutePath();

        //attach
        VirtualMachine vm = null;
        vm = VirtualMachine.attach("9730");//目标JVM的进程ID（PID）
        vm.loadAgent(agentJarPath);
        vm.detach();

        SpringApplication springApplication = new SpringApplication(AppOneApplication.class);
        springApplication.run(args);

    }
}