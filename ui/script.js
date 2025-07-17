let focusTime = 2 * 60; // 25 minutes
let breakTime = 1 * 60;  // 5 minutes
let currentTime = focusTime;
let timerInterval;
let isRunning = false;
let isFocusMode = true;
let sessionCount = 0;

const timerDisplay = document.getElementById('timer');
const focusBtn = document.getElementById('focusBtn');
const breakBtn = document.getElementById('breakBtn');
const startBtn = document.getElementById('startBtn');
const pauseBtn = document.getElementById('pauseBtn');
const resetBtn = document.getElementById('resetBtn');
const sessionDisplay = document.getElementById('sessionCount');

function updateTimerDisplay() {
    const minutes = String(Math.floor(currentTime / 60)).padStart(2, '0');
    const seconds = String(currentTime % 60).padStart(2, '0');
    timerDisplay.textContent = `${minutes}:${seconds}`;
}

function startTimer() {
    if (!isRunning) {
        isRunning = true;
        timerInterval = setInterval(() => {
            if (currentTime > 0) {
                currentTime--;
                updateTimerDisplay();
            } else {
                clearInterval(timerInterval);
                isRunning = false;
                if (isFocusMode) {
                    sessionCount++;
                    sessionDisplay.textContent = sessionCount;
                }
                // Optional: auto-switch or alert user
            }
        }, 1000);
    }
}

function pauseTimer() {
    clearInterval(timerInterval);
    isRunning = false;
}

function resetTimer() {
    pauseTimer();
    currentTime = isFocusMode ? focusTime : breakTime;
    updateTimerDisplay();
}

function switchToFocus() {
    pauseTimer();
    isFocusMode = true;
    currentTime = focusTime;
    focusBtn.classList.add('active');
    breakBtn.classList.remove('active');
    updateTimerDisplay();
}

function switchToBreak() {
    pauseTimer();
    isFocusMode = false;
    currentTime = breakTime;
    breakBtn.classList.add('active');
    focusBtn.classList.remove('active');
    updateTimerDisplay();
}

// Event listeners
startBtn.addEventListener('click', startTimer);
pauseBtn.addEventListener('click', pauseTimer);
resetBtn.addEventListener('click', resetTimer);
focusBtn.addEventListener('click', switchToFocus);
breakBtn.addEventListener('click', switchToBreak);

// Initial display
updateTimerDisplay();
