# ZS4C: Zero-Shot Synthesis of Compilable Code for Incomplete Code Snippets on Q&A Sites using ChatGPT

```
.
├── average_library_length.sh
├── average_loc.sh
├── README.md
├── Synthesis_of_Compilable_Code
│   ├── Codes
│   │   ├── Base_Prompt.ipynb
│   │   ├── Compilation_Fixing_with_LLM.ipynb
│   │   ├── Compilation_Rate.ipynb
│   │   ├── Conversation_Fixing_Benchmark.ipynb
│   │   ├── Matching_Degree_Calculation.ipynb
│   │   ├── One-shot_Import_Statement_Inference_with_and_without_SC.ipynb
│   │   ├── One-shot_with_Compilation_Fixing_Benchmark.ipynb
│   │   ├── SnR_Model_Benchmark.ipynb
│   │   ├── Zero-shot_Import_Statement_Inference_with_and_without_SC.ipynb
│   │   └── Zero-shot_ZS4C_and_Parameters_Impact_Benchmark.ipynb
│   └── Results
│       ├── compiled-classes
│       ├── final-benchmark
│       ├── llm_fixes
│       ├── logs
│       ├── Result_Base_Prompt.ipynb
│       ├── Result_Compilation_Fixing_with_LLM.ipynb
│       ├── Result_Compilation_Rate.ipynb
│       ├── Result_Conversation_Fixing_Benchmark.ipynb
│       ├── Result_Matching_Degree_Calculation.ipynb
│       ├── Result_One-shot_Import_Statement_Inference_with_and_without_SC.ipynb
│       ├── Result_One-shot_with_Compilation_Fixing_Benchmark.ipynb
│       ├── Result_SnR_Model_Benchmark.ipynb
│       ├── Result_Zero-shot_Import_Statement_Inference_with_and_without_SC.ipynb
│       ├── Result_Zero-shot_ZS4C_and_Parameters_Impact_Benchmark.ipynb
│       ├── so_dataset_compiled-classes
│       ├── so_dataset_logs
│       ├── so_dataset_with_imports_compiled
│       └── so_dataset_with_imports_logs
└── unique_library_count.sh
```

## Table of Contents
  - [Introduction](#introduction)
  - [Requirements](#requirements)
  - [Setup \& Installation](#setup--installation)
  - [Usage](#usage)
  - [References](#references)

### Introduction

We propose a lightweight tool named ZS4C for performing Zero-shot synthesis using ChatGPT (GPT-3.5 Turbo). This tool can be highly beneficial for solving incomplete code snippet and making it compilable. We used dataset from Q&A website (e.g., Stack Overflow) and compare our result with the SOTA approach SnR to ensure it's performance.

### Requirements

- We used the Python version 3.9.15. It is recommended to use the same version.
- Conda environment is preferred.
- Preferred operating system: Linux/macOS/Windows-WSL
- Need to install required Python libraries from [requirements.txt](requirements.txt).

### Setup & Installation

```bash
# Clone or download the repository

# Navigate to the directory
cd ZS4C

# Create a new conda environment
conda create --name myenv python=3.9.15  # Change 'myenv' to your desired conda environment name.

# Activate the conda environment
conda activate myenv  # Change 'myenv' to the name you set in the previous step.

# Install the required packages
conda install --file requirements.txt

# If some packages are not available in Conda, install with pip after activating the conda environment:
# pip install -r requirements.txt

# Launch Jupyter notebook
jupyter notebook
```

### Usage

ChatGPT's GPT 3.5 Turbo model was used to do the experiment. Here is a step-by-step guideline to execute our codes.

**Step 1**: We initially started with a [***BasePrompt***](Synthesis_of_Compilable_Code/Codes/Base_Prompt.ipynb). This file includes the benchmarks (Accuracy, F1, Recall, and Precision) and compilation rate for all 6 types of Java projects (Android, JDK, Hibernate, JodaTime, GWT, and XStream).
   
**Step 2**: We introduced a detailed prompt with more context and counted the benchmark for [***ImportStInfer<sub>noSC</sub>***](Synthesis_of_Compilable_Code/Codes/Zero-shot_Import_Statement_Inference_with_and_without_SC.ipynb) (Import Statement Inference without Zero-shot Self-Consistency) and with Self-Consistency [***ImportStInfer***](Synthesis_of_Compilable_Code/Codes/Zero-shot_Import_Statement_Inference_with_and_without_SC.ipynb) (with Zero-shot Self-Consistency) in the same file and appended the predicted import statements to the code body. This file is using a updated prompt with more context.
   
**Step 3**: Similar to previous step, we calculated the benchmarks for [One-shot](Synthesis_of_Compilable_Code/Codes/One-shot_Import_Statement_Inference_with_and_without_SC.ipynb) with different settings (e.g., using random example, semantic similar example) and appended the predicted import statements.
   
**Step 4**: We also counted the benchmark for the [***SnR***](Synthesis_of_Compilable_Code/Codes/SnR_Model_Benchmark.ipynb) [[1]](#ref1) model for better comparison.
   
**Step 5**: In this step, we calculated [Compilation Rate](Synthesis_of_Compilable_Code/Codes/Compilation_Rate.ipynb) for the appended import statements that we got from **Step 2** to **Step 4**.

**Step 6**: Now, we again used the ChatGPT to fix the compilation failed classes for all type by the [Compilation Fixing](Synthesis_of_Compilable_Code/Codes/Compilation_Fixing_with_LLM.ipynb). We gave the error message as a chat history context to fix the compilation error.

**Step 7**: From the compilation fixing we received the fixed code and from there we got the fix for Stack Overflow dataset (without import statements) too. Using that we got the benchmarks for [***ConversationFixing***](Synthesis_of_Compilable_Code/Codes/Conversation_Fixing_Benchmark.ipynb).

**Step 8**: Finally, we calculated the benchmarks for [***ZS4C***](Synthesis_of_Compilable_Code/Codes/Zero-shot_ZS4C_and_Parameters_Impact_Benchmark.ipynb) and also got the result for analyzing the impact of parameter for K samples.

A list wise [Matching Degree Calculation](Synthesis_of_Compilable_Code/Codes/Matching_Degree_Calculation.ipynb) is performed for *SnR*, *BasePrompt*, *ZS4C*, *ImportStInfer*, *ImportStInfer<sub>noSC</sub>*, and *ConversationFixing* for a better comparison.

All the benchmarks, logs, and notebook with results can be found in [Results](Synthesis_of_Compilable_Code/Results) folder. [Average Library Length](average_library_length.sh), [Average LOC](average_loc.sh), and [Unique Library Count](unique_library_count.sh) for Stack Overflow dataset can be printed using bash command.

### References

<a name="ref1"></a>
[1] Dong, Y., Gu, T., Tian, Y., & Sun, C. (2022, May). SnR: constraint-based type inference for incomplete Java code snippets. In Proceedings of the 44th International Conference on Software Engineering (pp. 1982-1993).