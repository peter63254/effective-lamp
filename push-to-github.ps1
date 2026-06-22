# Push Flower Shop Project to GitHub
# Usage:
#   1. Create a repository on GitHub (e.g., "flower-shop")
#   2. Run this script with your GitHub username:
#      .\push-to-github.ps1 -Username "your-username" -RepoName "flower-shop"
#   3. Or use a personal access token:
#      $env:GH_TOKEN = "your_token"
#      .\push-to-github.ps1 -Username "your-username" -RepoName "flower-shop"

param(
    [Parameter(Mandatory = $true)]
    [string]$Username,
    [Parameter(Mandatory = $true)]
    [string]$RepoName,
    [string]$Branch = "main"
)

$remoteUrl = "https://github.com/${Username}/${RepoName}.git"

# Check if remote already exists
$existing = git remote get-url origin 2>$null
if ($existing) {
    Write-Host "Remote origin already exists: $existing" -ForegroundColor Yellow
    Write-Host "Updating to: $remoteUrl" -ForegroundColor Yellow
    git remote set-url origin $remoteUrl
} else {
    git remote add origin $remoteUrl
}

# Rename master to main if needed
$currentBranch = git branch --show-current
if ($currentBranch -eq "master" -and $Branch -eq "main") {
    git branch -m master main
}

# Push to GitHub
Write-Host "Pushing to $remoteUrl ..." -ForegroundColor Green
git push -u origin $Branch

if ($LASTEXITCODE -eq 0) {
    Write-Host "Successfully pushed to GitHub!" -ForegroundColor Green
    Write-Host "Repository URL: https://github.com/${Username}/${RepoName}"
} else {
    Write-Host "Push failed. Make sure:" -ForegroundColor Red
    Write-Host "  1. The repository '$RepoName' exists on your GitHub account" -ForegroundColor Yellow
    Write-Host "  2. You have authentication set up:" -ForegroundColor Yellow
    Write-Host "     - Option A: Set env var: `$env:GH_TOKEN = 'your_token'" -ForegroundColor Yellow
    Write-Host "     - Option B: Install GitHub CLI (winget install GitHub.cli)" -ForegroundColor Yellow
    Write-Host "     - Option C: Use SSH: git remote set-url origin git@github.com:${Username}/${RepoName}.git" -ForegroundColor Yellow
}
