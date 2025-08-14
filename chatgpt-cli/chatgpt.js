#!/usr/bin/env node

import OpenAI from "openai";
import readlineSync from "readline-sync";
import chalk from "chalk";

// Your API key (embedded directly)
const apiKey = "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";
const client = new OpenAI({ apiKey });

console.log(chalk.cyan.bold("=== ChatGPT CLI (History Mode) ==="));
console.log(chalk.gray("Type 'exit' to quit\n"));

let history = [];

(async () => {
  while (true) {
    const userInput = readlineSync.question(chalk.green("You: "));
    if (userInput.toLowerCase() === "exit") break;

    history.push({ role: "user", content: userInput });

    try {
      const response = await client.chat.completions.create({
        model: "gpt-4o-mini",
        messages: history,
      });

      const aiResponse = response.choices[0].message.content.trim();
      console.log(chalk.yellow("AI:"), aiResponse);

      history.push({ role: "assistant", content: aiResponse });
    } catch (err) {
      console.error(chalk.red("Error:"), err.message);
    }
  }
})();
